package org.acme;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
@Path("/hello")
public class GreetingResourceImpl {

    private final JsonWebToken authenticationIdentity;

    @Inject
    public GreetingResourceImpl(JsonWebToken authenticationIdentity) {
        this.authenticationIdentity = authenticationIdentity;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"arole"})
    public String hello() {
        return "Hello " + authenticationIdentity.getSubject();
    }
}