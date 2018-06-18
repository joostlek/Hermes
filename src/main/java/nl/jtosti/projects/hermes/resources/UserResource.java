package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.UserResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserResource {
    @GET
    @Path("all")
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER})
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
    @RolesAllowed({AuthenticationResource.ROLE_ADVERTISING,
            AuthenticationResource.ROLE_OWNER,
            AuthenticationResource.ROLE_USER,
            AuthenticationResource.ROLE_SUPERUSER})
    @Path("me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMe(@Context SecurityContext context) {
        return getUser(Integer.parseInt(context.getUserPrincipal().getName()));
    }

    @GET
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER,
            AuthenticationResource.ROLE_ADVERTISING,
            AuthenticationResource.ROLE_OWNER,
            AuthenticationResource.ROLE_USER})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().get(id).toResponse()))
                .build();
    }

    @POST
    @RolesAllowed({AuthenticationResource.ROLE_GUEST})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().save(GsonProvider.getGson().fromJson(body, User.class)).toResponse()))
                .build();
    }

    @PUT
    @RolesAllowed({AuthenticationResource.ROLE_USER,
            AuthenticationResource.ROLE_ADVERTISING,
            AuthenticationResource.ROLE_OWNER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().update(GsonProvider.getGson().fromJson(body, User.class)).toResponse()))
                .build();
    }

    @DELETE
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getUserManager().delete(GsonProvider.getGson().fromJson(body, User.class))))
                .build();
    }
}
