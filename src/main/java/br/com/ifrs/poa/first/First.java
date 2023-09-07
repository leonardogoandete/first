package br.com.ifrs.poa.first;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/first")
public class First {
    private static final Logger LOGGER = Logger.getLogger(First.class.getName());

    @Inject
    @Claim(standard = Claims.full_name)
    String fullName;

    @Inject
    @RestClient
    SecondService secondService;

    private int count = 0;

    @GET
    @Path("/sum/{a}/{b}")
    @RolesAllowed({ "User" })
    @Produces(MediaType.TEXT_PLAIN)
    public long sum(@PathParam("a") long a, @PathParam("b") long b) {
        count+=1;
        LOGGER.log(Level.INFO, "{0} fez a chamada local numero {1}", new Object[]{fullName, count});
        return a + b;
    }


    @GET
    @Path("/second/{a}/{b}")
    @RolesAllowed({"Admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public int secondLocal(@PathParam("a") int a, @PathParam("b") int b){
        LOGGER.log(Level.INFO, "{0} no first fez a chamada para o serviço de soma, com valores {1}:{2}", new Object[]{fullName, a, b});
        return secondService.getSum(a,b);
    }
}
