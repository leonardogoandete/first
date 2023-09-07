package br.com.ifrs.poa.first;

import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/*
@RegisterRestClient(baseUri = "https://localhost:8445/second")
Estou utilizando properties para declarar a url base
 */
@RegisterRestClient()
@AccessToken
public interface SecondService {
    @GET
    @Path("/getSum/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"Admin"})
    int getSum(@PathParam("a") int a, @PathParam("b") int b);
}
