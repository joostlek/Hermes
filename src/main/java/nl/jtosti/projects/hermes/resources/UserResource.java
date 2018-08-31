package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.UserResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/user")
public class UserResource {

    @GET
    @Path("/all")
    @RolesAllowed({AuthResource.ROLE_SUPERUSER})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<UserResponse> users = new ArrayList<>();
        List<User> dbUsers = ManagerProvider.getUserManager().getAll();
        for (User user : dbUsers) {
            users.add(user.toResponse(true));
        }
        return Response
                .ok(GsonProvider.getGson().toJson(users))
                .build();
    }

    @GET
    @Path("{id: \\d+}")
    @RolesAllowed({AuthResource.ROLE_SUPERUSER})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@PathParam("id") int id) {
        try {
            User user = ManagerProvider.getUserManager().get(id);
            return Response
                    .ok(GsonProvider.getGson().toJson(user))
                    .build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(404)
                    .build();
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @RolesAllowed({AuthResource.ROLE_USER})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@Context SecContext context, @PathParam("id") int id, String body) {
        if (!context.getUserPrincipal().getName().equals(Integer.toString(id))) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        User user = GsonProvider.getGson().fromJson(body, User.class);
        if (user == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        User updatedUser = ManagerProvider.getUserManager().update(user);
        return Response
                .ok(GsonProvider.getGson().toJson(updatedUser))
                .build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String body) {
        User user = GsonProvider.getGson().fromJson(body, User.class);
        if (user == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        User createdUser = ManagerProvider.getUserManager().save(user);
        return Response
                .ok(GsonProvider.getGson().toJson(createdUser))
                .build();
    }

    @DELETE
    @Path("/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        User user = ManagerProvider.getUserManager().get(id);
        if (user == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        try {
            boolean success = ManagerProvider.getUserManager().delete(user);
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
}
