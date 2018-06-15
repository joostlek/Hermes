package nl.jtosti.projects.hermes.resources;

import com.google.gson.Gson;
import nl.jtosti.projects.hermes.ImageManager;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addImage(String body) {
        Gson gson = new Gson();
        Image image = gson.fromJson(body, Image.class);
        ImageManager imageManager = new ImageManager();
        imageManager.save(image);
        return gson.toJson(image);
    }
}
