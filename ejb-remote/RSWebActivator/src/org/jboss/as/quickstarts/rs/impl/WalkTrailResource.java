package org.jboss.as.quickstarts.rs.impl;


import org.jboss.as.quickstarts.rs.dao.WalkTrail;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import java.awt.print.Book;
import java.net.URI;

@Path("WalkTrailResource")
//@Stateless
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class WalkTrailResource {

    @Context
    private UriInfo uriInfo;

   // @EJB
   // MountainService mountainService;

    @GET
    @Produces("text/plain")
    public String getBookTitle() {
        return "H2G2";
    }

    /*
    @POST
    public Response createWalTrail(JAXBElement<WalkTrail> walkTrailJaxb) {
        WalkTrail walkTrail = walkTrailJaxb.getValue();

       // mountainService.createWalkTrail(walkTrail);

        URI bookUri = uriInfo.getAbsolutePathBuilder().path(walkTrail.getId().toString()).build();

        return Response.created(bookUri).build();
    }*/
}



