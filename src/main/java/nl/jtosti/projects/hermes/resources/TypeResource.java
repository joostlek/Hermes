package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Type;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.TypeResponse;
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

@Path("type")
public class TypeResource {
    @GET
    @Path("/location/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTypes(@PathParam("id") int id) {
        //TODO ADD CHECK FOR LOCATION EXIST
        List<TypeResponse> responses = new ArrayList<>();
        List<Type> types = ManagerProvider.getTypeManager().getAllByLocationId(id);
        for (Type type : types) {
            responses.add(type.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(responses))
                .build();
    }

    @GET
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTypeById(@PathParam("id") int id) {
        try {
            Type type = ManagerProvider.getTypeManager().get(id);
            return Response
                    .ok(GsonProvider.getGson().toJson(type.toResponse()))
                    .build();
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
    public Response updateType(@Context SecurityContext context, @PathParam("id") int id, String body) {
        try {
            Type type = ManagerProvider.getTypeManager().get(id);
            if (type.getLocation().getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                Type type1 = GsonProvider.getGson().fromJson(body, Type.class);
                type = ManagerProvider.getTypeManager().update(type1);
                return Response
                        .ok(GsonProvider.getGson().toJson(type))
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
    public Response deleteType(@Context SecurityContext context, @PathParam("id") int id, String body) {
        try {
            Type type = ManagerProvider.getTypeManager().get(id);
            if (type.getLocation().getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                boolean success = ManagerProvider.getTypeManager().delete(type);
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

    @POST
    @Path("/")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createType(String body) {
        // TODO Add check if user is manager of location
        Type type = GsonProvider.getGson().fromJson(body, Type.class);
        if (type == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        } else {
            return Response
                    .ok(GsonProvider.getGson().toJson(type.toResponse()))
                    .build();
        }
    }
}
