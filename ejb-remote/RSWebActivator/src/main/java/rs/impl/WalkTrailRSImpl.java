package rs.impl;


import rs.WalkTrailRS;
import rs.dao.WalkTrail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class WalkTrailRSImpl implements WalkTrailRS {

    @Context
    private UriInfo uriInfo;

  /*  @EJB
    private MountainBean mountainBean;*/

    static List<WalkTrail> walks = new ArrayList<>();

    static {
        walks.add(new WalkTrail(Long.valueOf(1), "gr20 corse", 198));
        walks.add(new WalkTrail(Long.valueOf(2), "gr10 pyrénées", 260));
        walks.add(new WalkTrail(Long.valueOf(3), "gr105 lot et garonne", 57));
        walks.add(new WalkTrail(Long.valueOf(4), "gr40 Loire", 78));
    }

    @PersistenceContext(unitName = "mountainPersistenceContextXA")
    private EntityManager em;

    @Override
    public String getBookTitle() {
        return "un jour un walk need!!";
    }

    @Override
    public String getExampleJson() {
        return "un jour un walk need en json bissss!!";
    }

    @Override
    public List<WalkTrail> getAllWalks() {
        //  Query query = em.createNativeQuery("SELECT * FROM WalkTrail", WalkTrail.class);
        //List<WalkTrail> walks = query.getResultList();
        return walks;
    }

    @Override
    public WalkTrail getWalk(@PathParam("walkid") String walkid) {
        return walks.stream().filter(w -> w.getId()==Long.valueOf(walkid)).findFirst().get();
    }

    @Override
    public WalkTrail getWalkQuery(@QueryParam("walkid") String walkid) {

        if (walkid.startsWith("-")) {
            throw new PersistenceException("invalid id exception");
        }

        return walks.stream().filter(w -> w.getId()==Long.valueOf(walkid)).findFirst().get();
    }

    @Override
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



