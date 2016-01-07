package org.jboss.as.quickstarts.rs.impl;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@Path("WalkTrailResource")
//@Stateless
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class WalkTrailResource {

    @Context
    private UriInfo uriInfo;

   // @EJB
   // MountainService mountainService;


    //@Produces({"text/plain", "application/xml", "application/json"})
    @GET
    @Path("example")
    @Produces("text/plain")
    public String getBookTitle() {
        return "un jour un walk rigolo!!";
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



