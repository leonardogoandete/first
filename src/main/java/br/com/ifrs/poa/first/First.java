package br.com.ifrs.poa.first;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/first")
public class First {

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
        System.out.println(fullName + " fez a chamada, nmr "+ count);
        return a + b;
    }


    @GET
    @Path("/second/{a}/{b}")
    @RolesAllowed({"Admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public int secondLocal(@PathParam("a") int a, @PathParam("b") int b){
        System.out.println(fullName + "no fisrt, chamou o serviço para soma");
        return secondService.getSum(a,b);
    }
}
