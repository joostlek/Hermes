package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Screen;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.ScreenResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/screens")
public class ScreenResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getScreens() {
        List<ScreenResponse> screenResponses = new ArrayList<>();
        for (Screen screen: ManagerProvider.getScreenManager().getAll()) {
            screenResponses.add(screen.toResponse());
        }
        return GsonProvider.getGson().toJson(screenResponses);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getScreen(@PathParam("id") int id) {
        return GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().get(id).toResponse());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addScreen(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().save(GsonProvider.getGson().fromJson(body, Screen.class)).toResponse());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateScreen(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().update(GsonProvider.getGson().fromJson(body, Screen.class)).toResponse());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteScreen(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getScreenManager().delete(GsonProvider.getGson().fromJson(body, Screen.class)));
    }
}
