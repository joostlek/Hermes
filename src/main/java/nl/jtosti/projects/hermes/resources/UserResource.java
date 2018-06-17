package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.UserResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: ManagerProvider.getUserManager().getAll()) {
            userResponses.add(user.toResponse());
        }
        return GsonProvider.getGson().toJson(userResponses);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") int id) {
        return GsonProvider.getGson().toJson(ManagerProvider.getUserManager().get(id).toResponse());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getUserManager().save(GsonProvider.getGson().fromJson(body, User.class)).toResponse());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getUserManager().update(GsonProvider.getGson().fromJson(body, User.class)).toResponse());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getUserManager().delete(GsonProvider.getGson().fromJson(body, User.class)));
    }
}
