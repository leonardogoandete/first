package br.com.ifrs.poa.first;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@Path("/first")
public class First {

    @Inject
    @Claim(standard = Claims.full_name)
    String fullName;

    @GET
    @Path("/sum/{a}/{b}")
    @RolesAllowed({ "User" })
    @Produces(MediaType.TEXT_PLAIN)
    public long sum(@PathParam("a") long a, @PathParam("b") long b) {
        return a + b;
    }
}
