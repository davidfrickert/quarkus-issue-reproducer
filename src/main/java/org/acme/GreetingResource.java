package org.acme;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public interface GreetingResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@RolesAllowed({"arole"})
	String hello();
}
