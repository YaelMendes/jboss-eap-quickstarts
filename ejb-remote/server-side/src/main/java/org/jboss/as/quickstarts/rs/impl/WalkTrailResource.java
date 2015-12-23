package org.jboss.as.quickstarts.rs.impl;

import org.jboss.as.quickstarts.dao.WalkTrail;
import org.jboss.as.quickstarts.service.MountainService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import java.awt.print.Book;
import java.net.URI;

@Path("WalkTrailResource")
@Stateless
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class WalkTrailResource {

    @Context
    private UriInfo uriInfo;

    @EJB
    MountainService mountainService;

    @POST
    public Response createWalTrail(JAXBElement<WalkTrail> walkTrailJaxb) {
        WalkTrail walkTrail = walkTrailJaxb.getValue();

        mountainService.createWalkTrail(walkTrail);

        URI bookUri = uriInfo.getAbsolutePathBuilder().path(walkTrail.getId().toString()).build();

        return Response.created(bookUri).build();
    }
}



