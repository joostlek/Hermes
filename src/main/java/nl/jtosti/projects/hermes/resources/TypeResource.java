package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Type;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.TypeResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/types")
public class TypeResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTypes() {
        List<TypeResponse> typeResponses = new ArrayList<>();
        for (Type type: ManagerProvider.getTypeManager().getAll()) {
            typeResponses.add(type.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(typeResponses))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().get(id).toResponse()))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateType(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().update(GsonProvider.getGson().fromJson(body, Type.class)).toResponse()))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addType(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().save(GsonProvider.getGson().fromJson(body, Type.class)).toResponse()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteType(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().save(GsonProvider.getGson().fromJson(body, Type.class))))
                .build();
    }
}
