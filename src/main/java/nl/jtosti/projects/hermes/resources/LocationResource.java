package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.LocationResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/locations")
public class LocationResource {
    @GET
    @Path("/all")
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER})
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
    @Path("/simple")
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER, AuthenticationResource.ROLE_OWNER, AuthenticationResource.ROLE_ADVERTISING, AuthenticationResource.ROLE_USER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimpleLocations() {
        List<LocationResponse> locationResponses = new ArrayList<>();
        for (Location location: ManagerProvider.getLocationManager().getAll()) {
            locationResponses.add(location.toResponse(true));
        }
        return Response
                .ok(GsonProvider.getGson().toJson(locationResponses))
                .build();
    }

    @GET
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyLocations(@Context SecurityContext context) {
        User user = ManagerProvider.getUserManager().get(context.getUserPrincipal().getName());
        List<LocationResponse> locationResponses = new ArrayList<>();
        for (Location location: user.getLocations()) {
            locationResponses.add(location.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(locationResponses))
                .build();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({AuthenticationResource.ROLE_OWNER, AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocation(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().get(id).toResponse()))
                .build();
    }

    @POST
    @RolesAllowed({AuthenticationResource.ROLE_USER, AuthenticationResource.ROLE_OWNER, AuthenticationResource.ROLE_OWNER_AD})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLocation(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().save(GsonProvider.getGson().fromJson(body, Location.class)).toResponse()))
                .build();
    }

    @PUT
    @RolesAllowed({AuthenticationResource.ROLE_OWNER, AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLocation(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().update(GsonProvider.getGson().fromJson(body, Location.class)).toResponse()))
                .build();
    }

    @DELETE
    @RolesAllowed({AuthenticationResource.ROLE_OWNER, AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLocation(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getLocationManager().delete(GsonProvider.getGson().fromJson(body, Location.class))))
                .build();
    }
}
