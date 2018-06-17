package nl.jtosti.projects.hermes.resources;

import com.google.gson.Gson;
import nl.jtosti.projects.hermes.persistence.ImageManager;
import nl.jtosti.projects.hermes.models.Image;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/images")
public class ImageResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getImages() {
        Gson gson = new Gson();
        ImageManager imageManager = new ImageManager();
        return gson.toJson(imageManager.getAll());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getImage(@PathParam("id") int id) {
        Gson gson = new Gson();
        ImageManager imageManager = new ImageManager();
        return gson.toJson(imageManager.get(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addImage(String body) {
        Gson gson = new Gson();
        ImageManager imageManager = new ImageManager();
        return gson.toJson(imageManager.save(gson.fromJson(body, Image.class)));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateImage(String body) {
        Gson gson = new Gson();
        ImageManager imageManager = new ImageManager();
        return gson.toJson(imageManager.update(gson.fromJson(body, Image.class)));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteImage(String body) {
        Gson gson = new Gson();
        ImageManager imageManager = new ImageManager();
        return gson.toJson(imageManager.delete(gson.fromJson(body, Image.class)));
    }
}
