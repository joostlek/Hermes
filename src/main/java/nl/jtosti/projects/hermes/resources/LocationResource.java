package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.LocationResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/locations")
public class LocationResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations() {
        List<LocationResponse> locationResponses = new ArrayList<>();
        for (Location location: ManagerProvider.getLocationManager().getAll()) {
            locationResponses.add(location.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(locationResponses))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocation(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().get(id).toResponse()))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLocation(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().save(GsonProvider.getGson().fromJson(body, Location.class)).toResponse()))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLocation(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().update(GsonProvider.getGson().fromJson(body, Location.class)).toResponse()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLocation(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().delete(GsonProvider.getGson().fromJson(body, Location.class))))
                .build();
    }
}
