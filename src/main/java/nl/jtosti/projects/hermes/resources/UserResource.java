package nl.jtosti.projects.hermes.resources;

import com.google.gson.JsonSyntaxException;
import nl.jtosti.projects.hermes.models.Type;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.UserResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
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
                    .ok(GsonProvider.getGson().toJson(user.toResponse()))
                    .build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    @Path("/{id: \\d+}")
    @RolesAllowed({AuthResource.ROLE_USER})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@Context SecurityContext context, @PathParam("id") int id, String body) {
        try {
            if (id == Integer.parseInt(context.getUserPrincipal().getName())) {
                User user = ManagerProvider.getUserManager().get(id);
                User user1 = GsonProvider.getGson().fromJson(body, User.class);
                if (user.getId() == user1.getId()) {
                    user = ManagerProvider.getUserManager().update(user1);
                    return Response
                            .ok(GsonProvider.getGson().toJson(user))
                            .build();
                } else {
                    return Response
                            .status(Response.Status.UNAUTHORIZED)
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
        } catch (JsonSyntaxException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String body) {
        try {
            User user = GsonProvider.getGson().fromJson(body, User.class);
            User createdUser = ManagerProvider.getUserManager().save(user);
            return Response
                    .ok(GsonProvider.getGson().toJson(createdUser))
                    .build();
        } catch (JsonSyntaxException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        } catch (RollbackException e) {
            System.out.println(e.getCause().getCause().getCause().getMessage());
            if (e.getCause().getCause().getCause().toString().contains("email")) {
                return Response
                        .status(Response.Status.CONFLICT)
                        .build();
            } else {
                return Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @DELETE
    @Path("/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            if (id != Integer.parseInt(context.getUserPrincipal().getName())) {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
            User user = ManagerProvider.getUserManager().get(id);
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
