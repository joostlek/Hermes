package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Type;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.TypeResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/types")
public class TypeResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTypes() {
        List<TypeResponse> typeResponses = new ArrayList<>();
        for (Type type: ManagerProvider.getTypeManager().getAll()) {
            typeResponses.add(type.toResponse());
        }
        return GsonProvider.getGson().toJson(typeResponses);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getType(@PathParam("id") int id) {
        return GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().get(id).toResponse());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateType(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().update(GsonProvider.getGson().fromJson(body, Type.class)).toResponse());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addType(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().save(GsonProvider.getGson().fromJson(body, Type.class)).toResponse());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteType(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getTypeManager().save(GsonProvider.getGson().fromJson(body, Type.class)));
    }
}
