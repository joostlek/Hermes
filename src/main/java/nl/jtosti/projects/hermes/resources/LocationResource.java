package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.LocationResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/locations")
public class LocationResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLocations() {
        List<LocationResponse> locationResponses = new ArrayList<>();
        for (Location location: ManagerProvider.getLocationManager().getAll()) {
            locationResponses.add(location.toResponse());
        }
        return GsonProvider.getGson().toJson(locationResponses);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addLocation(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().save(GsonProvider.getGson().fromJson(body, Location.class)).toResponse());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateLocation(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().update(GsonProvider.getGson().fromJson(body, Location.class)).toResponse());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteLocation(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().delete(GsonProvider.getGson().fromJson(body, Location.class)));
    }
}
