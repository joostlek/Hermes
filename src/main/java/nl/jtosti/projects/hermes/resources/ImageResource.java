package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/images")
public class ImageResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getImages() {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().getAll());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getImage(@PathParam("id") int id) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().get(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().save(GsonProvider.getGson().fromJson(body, Image.class)));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().update(GsonProvider.getGson().fromJson(body, Image.class)));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().delete(GsonProvider.getGson().fromJson(body, Image.class)));
    }
}
