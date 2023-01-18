package org.acme;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/hello")
public class GreetingResourceImpl {

    private final AuthenticationIdentity authenticationIdentity;

    @Inject
    public GreetingResourceImpl(AuthenticationIdentity authenticationIdentity) {
        this.authenticationIdentity = authenticationIdentity;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"arole"})
    public String hello() {
        return "Hello " + authenticationIdentity.getSubject();
    }
}