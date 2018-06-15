package nl.jtosti.projects.hermes.resources;

import com.google.gson.Gson;
import nl.jtosti.projects.hermes.UserManager;
import nl.jtosti.projects.hermes.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        return gson.toJson(userManager.getAll());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String body) {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        User user = gson.fromJson(body, User.class);
        return gson.toJson(userManager.save(user));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(String body) {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        User user = gson.fromJson(body, User.class);
        return gson.toJson(userManager.update(user));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(String body) {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        User user = gson.fromJson(body, User.class);
        return gson.toJson(userManager.delete(user));
    }
}
