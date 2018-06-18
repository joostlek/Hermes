package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.ImageResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/images")
public class ImageResource {
    @GET
    @Path("/all")
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public String getImages() {
        List<ImageResponse> imageResponses = new ArrayList<>();
        for (Image image: ManagerProvider.getImageManager().getAll()) {
            imageResponses.add(image.toResponse());
        }
        return GsonProvider.getGson().toJson(imageResponses);
    }

    @GET
    @RolesAllowed({AuthenticationResource.ROLE_ADVERTISING})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyImages(@Context SecurityContext context) {
        User user = ManagerProvider.getUserManager().get(Integer.parseInt(context.getUserPrincipal().getName()));
        List<ImageResponse> imageResponses = new ArrayList<>();
        for (Image image: user.getImages()) {
            imageResponses.add(image.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(imageResponses))
                .build();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER,
            AuthenticationResource.ROLE_ADVERTISING,
            AuthenticationResource.ROLE_OWNER})
    @Produces(MediaType.APPLICATION_JSON)
    public String getImage(@PathParam("id") int id) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().get(id).toResponse());
    }

    @POST
    @RolesAllowed({AuthenticationResource.ROLE_ADVERTISING})
    @Produces(MediaType.APPLICATION_JSON)
    public String addImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().save(GsonProvider.getGson().fromJson(body, Image.class)).toResponse());
    }

    @PUT
    @RolesAllowed({AuthenticationResource.ROLE_ADVERTISING})
    @Produces(MediaType.APPLICATION_JSON)
    public String updateImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().update(GsonProvider.getGson().fromJson(body, Image.class)).toResponse());
    }

    @DELETE
    @RolesAllowed({AuthenticationResource.ROLE_ADVERTISING})
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteImage(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getImageManager().delete(GsonProvider.getGson().fromJson(body, Image.class)));
    }
}
