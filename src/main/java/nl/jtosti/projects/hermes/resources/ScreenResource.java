package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.models.Screen;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.ScreenResponse;
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

@Path("/screen/")
public class ScreenResource {

    @GET
    @Path("/")
    @RolesAllowed(AuthResource.ROLE_SUPERUSER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllScreens() {
        List<ScreenResponse> responses = new ArrayList<>();
        List<Screen> screens = ManagerProvider.getScreenManager().getAll();
        for (Screen screen : screens) {
            responses.add(screen.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(responses))
                .build();
    }

    @GET
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScreenById(@PathParam("id") int id) {
        try {
            Screen screen = ManagerProvider.getScreenManager().get(id);
            return Response
                    .ok(screen.toResponse())
                    .build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Path("/location/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScreensByLocationId(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            boolean authorized = context.isUserInRole(AuthResource.ROLE_SUPERUSER) || ManagerProvider.getLocationManager().isUserAssociated(id, Integer.parseInt(context.getUserPrincipal().getName()));
            if  (authorized) {
                List<Screen> screens = ManagerProvider.getScreenManager().getAllByLocationId(id);
                List<ScreenResponse> responses = new ArrayList<>();
                for (Screen screen : screens) {
                    responses.add(screen.toResponse());
                }
                return Response
                        .ok(GsonProvider.getGson().toJson(responses))
                        .build();
            } else {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateScreen(@Context SecurityContext context, @PathParam("id") int id, String body) {
        try {
            Screen screen = ManagerProvider.getScreenManager().get(id);
            if (screen.getLocation().getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                Screen screen1 = GsonProvider.getGson().fromJson(body, Screen.class);
                screen = ManagerProvider.getScreenManager().update(screen1);
                return Response
                        .ok(GsonProvider.getGson().toJson(screen.toResponse()))
                        .build();
            } else {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
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
    public Response deleteScreen(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            Screen screen = ManagerProvider.getScreenManager().get(id);
            if (screen.getLocation().getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                boolean success = ManagerProvider.getScreenManager().delete(screen);
                if (success) {
                    return Response
                            .ok()
                            .build();
                } else {
                    return Response
                            .status(Response.Status.NOT_MODIFIED)
                            .build();
                }
            } else {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
