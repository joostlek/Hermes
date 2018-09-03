package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.*;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.ImageResponse;
import nl.jtosti.projects.hermes.responses.LocationResponse;
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

@Path("/image")
public class ImageResource {

    @GET
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImageById(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            Image image = ManagerProvider.getImageManager().get(id);
            return Response
                    .ok(GsonProvider.getGson().toJson(image.toResponse()))
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
    public Response updateImage(@Context SecurityContext context, @PathParam("id") int id, String body) {
        try {
            Image image = ManagerProvider.getImageManager().get(id);
            if (image.getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                Image givenImage = GsonProvider.getGson().fromJson(body, Image.class);
                image = ManagerProvider.getImageManager().update(givenImage);
                return Response
                        .ok(GsonProvider.getGson().toJson(image.toResponse()))
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteImage(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            Image image = ManagerProvider.getImageManager().get(id);
            if (image.getOwner().getId() != Integer.parseInt(context.getUserPrincipal().getName())) {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
            boolean success = ManagerProvider.getImageManager().delete(image);
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

    @POST
    @Path("/")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createImage(@Context SecurityContext context, String body) {
        Image image = GsonProvider.getGson().fromJson(body, Image.class);
        if (image == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        if (image.getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
            image = ManagerProvider.getImageManager().save(image);
            return Response
                    .ok(GsonProvider.getGson().toJson(image.toResponse()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
    }

    @GET
    @Path("/location/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImageByLocationId(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            if (ManagerProvider.getLocationManager().isUserAssociated(id, Integer.parseInt(context.getUserPrincipal().getName()))) {
                List<ImageResponse> responses = new ArrayList<>();
                List<Image> images = ManagerProvider.getImageManager().getAllByLocationId(id);
                for (Image image : images) {
                    responses.add(image.toResponse());
                }
                return Response
                        .ok(GsonProvider.getGson().toJson(responses))
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

    @GET
    @Path("/location/{id: \\d+}/inactive")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUncheckedImagesByLocationId(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            if (ManagerProvider.getLocationManager().isUserAssociated(id, Integer.parseInt(context.getUserPrincipal().getName()))) {
                List<ImageResponse> responses = new ArrayList<>();
                List<Image> images = ManagerProvider.getImageManager().getUncheckedImagesByLocationId(id);
                for (Image image : images) {
                    responses.add(image.toResponse());
                }
                return Response
                        .ok(GsonProvider.getGson().toJson(responses))
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

    @GET
    @Path("/screen/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImagesByScreenID(@PathParam("id") int id) {
        try {
            List<ImageResponse> responses = new ArrayList<>();
            List<Image> images = ManagerProvider.getImageManager().getActiveByScreenId(id);
            for (Image image : images) {
                responses.add(image.toScreenResponse());
            }
            return Response
                    .ok(GsonProvider.getGson().toJson(responses))
                    .build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    @Path("/{id: \\d+}/active")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateActiveImage(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            Image image = ManagerProvider.getImageManager().get(id);
            if (image.getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                image = ManagerProvider.getImageManager().updateActive(image);
                return Response
                        .ok(GsonProvider.getGson().toJson(image.toResponse()))
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
}
