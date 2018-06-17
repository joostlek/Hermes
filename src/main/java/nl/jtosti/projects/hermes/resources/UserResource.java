package nl.jtosti.projects.hermes.resources;

import com.google.gson.Gson;
import nl.jtosti.projects.hermes.persistence.UserManager;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.responses.UserResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: userManager.getAll()) {
            userResponses.add(user.toResponse());
        }
        return gson.toJson(userResponses);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") int id) {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        return gson.toJson(userManager.get(id).toResponse());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String body) {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        return gson.toJson(userManager.save(gson.fromJson(body, User.class)).toResponse());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(String body) {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        return gson.toJson(userManager.update(gson.fromJson(body, User.class)).toResponse());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(String body) {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        return gson.toJson(userManager.delete(gson.fromJson(body, User.class)));
    }
}
