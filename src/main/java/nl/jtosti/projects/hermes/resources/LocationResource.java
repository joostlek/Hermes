package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.models.Roles;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.LocationResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/location")
public class LocationResource {
    @GET
    @Path("/all")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations() {
        List<LocationResponse> locations = new ArrayList<>();
        List<Location> dbLocations = ManagerProvider.getLocationManager().getAll();
        for (Location location : dbLocations) {
            locations.add(location.toResponse(true));
        }
        return Response
                .ok(GsonProvider.getGson().toJson(locations))
                .build();
    }

    @GET
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocationById(@PathParam("id") int id) {
        try {
            Location location = ManagerProvider.getLocationManager().get(id);
            return Response
                    .ok(GsonProvider.getGson().toJson(location.toResponse()))
                    .build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    @Path("/")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLocation(@Context SecurityContext context, String body) {
        Location location = GsonProvider.getGson().fromJson(body, Location.class);
        if (location == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        Location dbLocation = ManagerProvider.getLocationManager().save(location);
        User manager = ManagerProvider.getUserManager().get(Integer.parseInt(context.getUserPrincipal().getName()));
        dbLocation.addUser(manager, Roles.MANAGER);
        dbLocation = ManagerProvider.getLocationManager().update(dbLocation);
        return Response
                .ok(GsonProvider.getGson().toJson(dbLocation.toResponse()))
                .build();
    }

    @PUT
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLocation(@Context SecurityContext context, @PathParam("id") int id, String body) {
        try {
            Location location = GsonProvider.getGson().fromJson(body, Location.class);
            Location dbLocation = ManagerProvider.getLocationManager().get(id);
            if (dbLocation.getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
            location = ManagerProvider.getLocationManager().update(location);
            return Response
                    .ok(location.toResponse())
                    .build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @DELETE
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteLocation(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            Location location = ManagerProvider.getLocationManager().get(id);
            if (location.getOwner().getId() != Integer.parseInt(context.getUserPrincipal().getName())) {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
            boolean success = ManagerProvider.getLocationManager().delete(location);
            if (success) {
                return Response
                        .ok()
                        .build();
            } else {
                return Response
                        .status(Response.Status.NOT_MODIFIED)
                        .build();
            }
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Path("/my")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyLocations(@Context SecurityContext context) {
        List<LocationResponse> responses = new ArrayList<>();
        List<Location> locations = ManagerProvider.getLocationManager().getLocationsByUserId(Integer.parseInt(context.getUserPrincipal().getName()));
        for (Location location : locations) {
            responses.add(location.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(responses))
                .build();
    }
}
