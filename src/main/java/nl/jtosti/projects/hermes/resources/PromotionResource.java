package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Promotion;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.PromotionResponse;
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

@Path("/promotion")
public class PromotionResource {

    @GET
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotionById(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            Promotion promotion = ManagerProvider.getPromotionManager().get(id);
            if (ManagerProvider.getLocationManager().isUserAssociated(promotion.getType().getLocation().getId(), Integer.parseInt(context.getUserPrincipal().getName()))) {
                return Response
                        .ok(GsonProvider.getGson().toJson(promotion.toResponse()))
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
    @Path("/location/{id: \\d+}/all")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotionsByLocationId(@Context SecurityContext context, @PathParam("id") int id) {
        if (ManagerProvider.getLocationManager().get(id).getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
            List<PromotionResponse> responses = new ArrayList<>();
            List<Promotion> promotions = ManagerProvider.getPromotionManager().getPromotionsByLocationId(id);
            for (Promotion promotion : promotions) {
                responses.add(promotion.toResponse());
            }
            return Response
                    .ok(GsonProvider.getGson().toJson(responses))
                    .build();
        }
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }

    @GET
    @Path("/location/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotionsByLocationIdByUser(@Context SecurityContext context, @PathParam("id") int id) {
        if (ManagerProvider.getLocationManager().isUserAssociated(id, Integer.parseInt(context.getUserPrincipal().getName()))) {
            List<PromotionResponse> responses = new ArrayList<>();
            List<Promotion> promotions = ManagerProvider.getPromotionManager().getPromotionsByLocationIdByUserId(id, Integer.parseInt(context.getUserPrincipal().getName()));
            for (Promotion promotion : promotions) {
                responses.add(promotion.toResponse());
            }
            return Response
                    .ok(GsonProvider.getGson().toJson(responses))
                    .build();
        }
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }

    @PUT
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePromotion(@Context SecurityContext context, @PathParam("id") int id, String body) {
        try {
            Promotion promotion = ManagerProvider.getPromotionManager().get(id);
            if (promotion.getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                Promotion givenPromotion = GsonProvider.getGson().fromJson(body, Promotion.class);
                promotion = ManagerProvider.getPromotionManager().update(givenPromotion);
                return Response
                        .ok(GsonProvider.getGson().toJson(promotion.toResponse()))
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
    public Response deletePromotion(@Context SecurityContext context, @PathParam("id") int id) {
        try {
            Promotion promotion = ManagerProvider.getPromotionManager().get(id);
            if (promotion.getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
                boolean success = ManagerProvider.getPromotionManager().delete(promotion);
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
    @Path("/{id: \\d+}")
    @RolesAllowed(AuthResource.ROLE_USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPromotion(@Context SecurityContext context, String body) {
        Promotion promotion = GsonProvider.getGson().fromJson(body, Promotion.class);
        if (promotion == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        if (promotion.getOwner().getId() == Integer.parseInt(context.getUserPrincipal().getName())) {
            promotion = ManagerProvider.getPromotionManager().save(promotion);
            return Response
                    .ok(GsonProvider.getGson().toJson(promotion.toResponse()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
    }
}
