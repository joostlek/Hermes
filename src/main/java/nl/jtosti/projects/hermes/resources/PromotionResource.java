package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Promotion;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.PromotionResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/promotions")
public class PromotionResource {
    @GET
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
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotion(@PathParam("id") int id) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().get(id).toResponse()))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPromotion(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().save(GsonProvider.getGson().fromJson(body, Promotion.class)).toResponse()))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePromotion(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().update(GsonProvider.getGson().fromJson(body, Promotion.class)).toResponse()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePromotion(String body) {
        return Response
                .ok(GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().delete(GsonProvider.getGson().fromJson(body, Promotion.class))))
                .build();
    }
}
