package org.acme;

import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Path("/hello")
public class GreetingResourceImpl {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"arole"})
    public String hello() {
        return "{}";
    }

    @ServerExceptionMapper
    public Response authorizationFailedHandler(final ForbiddenException exception) {
        return Response.status(Status.FORBIDDEN)
                       .entity("")
                       .build();
    }

    @ServerExceptionMapper
    public Response authenticationFailedHandler(final UnauthorizedException exception) {
        return Response.status(Status.UNAUTHORIZED)
                       .entity("")
                       .build();
    }

    @ServerExceptionMapper
    public Response unexpectedExceptionHandler(final RuntimeException exception) {
        // WebApplicationException already translates directly to a known http status code with a message
        if (exception instanceof WebApplicationException wae) {
            return wae.getResponse();
        }

        return Response.status(Status.INTERNAL_SERVER_ERROR)
                       .entity("")
                       .build();
    }

}