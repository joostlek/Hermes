package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.UserResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: ManagerProvider.getUserManager().getAll()) {
            userResponses.add(user.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(userResponses))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().get(id).toResponse()))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().save(GsonProvider.getGson().fromJson(body, User.class)).toResponse()))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().update(GsonProvider.getGson().fromJson(body, User.class)).toResponse()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().delete(GsonProvider.getGson().fromJson(body, User.class))))
                .build();
    }
}
