package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Promotion;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.PromotionResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/promotions")
public class PromotionResource {
    @GET
    @Path("/all")
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotions() {
        List<PromotionResponse> promotionResponses = new ArrayList<>();
        for (Promotion promotion: ManagerProvider.getPromotionManager().getAll()) {
            promotionResponses.add(promotion.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(promotionResponses))
                .build();
    }

    @GET
    @RolesAllowed({AuthenticationResource.ROLE_ADVERTISING,
            AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyPromotions(@Context SecurityContext context) {
        User user = ManagerProvider.getUserManager().get(context.getUserPrincipal().getName());
        List<PromotionResponse> promotionResponses = new ArrayList<>();
        for (Promotion promotion: user.getPromotions()) {
            promotionResponses.add(promotion.toResponse());
        }
        return Response
                .ok(GsonProvider.getGson().toJson(promotionResponses))
                .build();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({AuthenticationResource.ROLE_ADVERTISING,
            AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotion(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().get(id).toResponse()))
                .build();
    }

    @POST
    @RolesAllowed({AuthenticationResource.ROLE_OWNER,
            AuthenticationResource.ROLE_ADVERTISING,
            AuthenticationResource.ROLE_USER,
            AuthenticationResource.ROLE_SUPERUSER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPromotion(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().save(GsonProvider.getGson().fromJson(body, Promotion.class)).toResponse()))
                .build();
    }

    @PUT
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER,
            AuthenticationResource.ROLE_ADVERTISING})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePromotion(String body) {
        Promotion promotion = ManagerProvider.getPromotionManager().update(GsonProvider.getGson().fromJson(body, Promotion.class));
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().get(promotion.getId()).toResponse()))
                .build();
    }

    @DELETE
    @RolesAllowed({AuthenticationResource.ROLE_SUPERUSER,
            AuthenticationResource.ROLE_ADVERTISING})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePromotion(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().delete(GsonProvider.getGson().fromJson(body, Promotion.class))))
                .build();
    }
}
