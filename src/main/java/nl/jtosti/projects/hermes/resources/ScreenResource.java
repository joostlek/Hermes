package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Screen;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.ScreenResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/screens")
public class ScreenResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScreens() {
        List<ScreenResponse> screenResponses = new ArrayList<>();
        for (Screen screen: ManagerProvider.getScreenManager().getAll()) {
            screenResponses.add(screen.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(screenResponses))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScreen(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().get(id).toResponse()))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addScreen(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().save(GsonProvider.getGson().fromJson(body, Screen.class)).toResponse()))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateScreen(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().update(GsonProvider.getGson().fromJson(body, Screen.class)).toResponse()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteScreen(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().delete(GsonProvider.getGson().fromJson(body, Screen.class))))
                .build();
    }
}
