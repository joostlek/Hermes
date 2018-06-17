package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.ImageResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/images")
public class ImageResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getImages() {
        List<ImageResponse> imageResponses = new ArrayList<>();
        for (Image image: ManagerProvider.getImageManager().getAll()) {
            imageResponses.add(image.toResponse());
        }
        return GsonProvider.getGson().toJson(imageResponses);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getImage(@PathParam("id") int id) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().get(id).toResponse());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().save(GsonProvider.getGson().fromJson(body, Image.class)).toResponse());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().update(GsonProvider.getGson().fromJson(body, Image.class)).toResponse());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().delete(GsonProvider.getGson().fromJson(body, Image.class)));
    }
}
