package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Promotion;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.responses.PromotionResponse;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/promotions")
public class PromotionResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPromotions() {
        List<PromotionResponse> promotionResponses = new ArrayList<>();
        for (Promotion promotion: ManagerProvider.getPromotionManager().getAll()) {
            promotionResponses.add(promotion.toResponse());
        }
        return GsonProvider.getGson().toJson(promotionResponses);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPromotion(@PathParam("id") int id) {
        return GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().get(id).toResponse());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addPromotion(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().save(GsonProvider.getGson().fromJson(body, Promotion.class)).toResponse());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePromotion(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().update(GsonProvider.getGson().fromJson(body, Promotion.class)).toResponse());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePromotion(String body) {
        return GsonProvider.getGson().toJson(ManagerProvider.getPromotionManager().delete(GsonProvider.getGson().fromJson(body, Promotion.class)));
    }
}
