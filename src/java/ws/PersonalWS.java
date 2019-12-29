/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Gmeruvia
 */
@Path("servicePersonal")
public class PersonalWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonalWS
     */
    public PersonalWS() {
    }

    /**
     * Retrieves representation of an instance of ws.PersonalWS
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("listadoPersonal")
    public String getXml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "Hello";
    }

    /**
     * PUT method for updating or creating an instance of PersonalWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
