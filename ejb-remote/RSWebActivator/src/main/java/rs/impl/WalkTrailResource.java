package rs.impl;


import rs.dao.WalkTrail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Path("walks")
//@Stateless
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class WalkTrailResource {

    @Context
    private UriInfo uriInfo;

    // @EJB
    // MountainService mountainService;

    static List<WalkTrail> walks = new ArrayList<>();

    static {
        walks.add(new WalkTrail(Long.valueOf(1), "gr20 corse", 198));
        walks.add(new WalkTrail(Long.valueOf(2), "gr10 pyrénées", 260));
        walks.add(new WalkTrail(Long.valueOf(3), "gr105 lot et garonne", 57));
        walks.add(new WalkTrail(Long.valueOf(4), "gr40 Loire", 78));
    }

    @PersistenceContext(unitName = "mountainPersistenceContextXA")
    private EntityManager em;

    @GET
    @Path("example")
    @Produces("text/plain")
    public String getBookTitle() {
        return "un jour un walk need!!";
    }

    @GET
    @Path("exampleJson")
    @Produces("application/json")
    public String getExampleJson() {
        return "un jour un walk need en json bissss!!";
    }

    @GET
    @Path("allWalks")
    @Produces( "application/json")
    public List<WalkTrail> getAllWalks() {
        //  Query query = em.createNativeQuery("SELECT * FROM WalkTrail", WalkTrail.class);
        //List<WalkTrail> walks = query.getResultList();
        return walks;
    }

    @GET
    @Path("/operations/{walkid}")
    @Produces("application/json")
    public WalkTrail getWalk(@PathParam("walkid") String walkid) {
        return walks.stream().filter(w -> w.getId()==Long.valueOf(walkid)).findFirst().get();
    }

    @GET
    @Path("/operations")
    @Produces("application/json")
    public WalkTrail getWalkQuery(@QueryParam("walkid") String walkid) {
        return walks.stream().filter(w -> w.getId()==Long.valueOf(walkid)).findFirst().get();
    }

    @GET
    @Path("longest")
    @Produces("application/json")
    public WalkTrail getLongest() {
        return  walks.stream().max(Comparator.naturalOrder()).get();
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



