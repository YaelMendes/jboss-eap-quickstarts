package rs;

import rs.dao.WalkTrail;

import javax.ws.rs.*;
import java.util.List;

@Path("walks")
public interface WalkTrailRS {

    @GET
    @Path("example")
    @Produces("text/plain")
    String getBookTitle();

    @GET
    @Path("exampleJson")
    @Produces("application/json")
    String getExampleJson();

    @GET
    @Path("allWalks")
    @Produces( "application/json")
    List<WalkTrail> getAllWalks();

    @GET
    @Path("/operations/{walkid}")
    @Produces("application/json")
    WalkTrail getWalk(@PathParam("walkid") String walkid);

    @GET
    @Path("/operations")
    @Produces("application/json")
    WalkTrail getWalkQuery(@QueryParam("walkid") String walkid);

    @GET
    @Path("longest")
    @Produces("application/json")
    WalkTrail getLongest();
}
