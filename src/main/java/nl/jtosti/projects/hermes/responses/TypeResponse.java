package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Promotion;
import nl.jtosti.projects.hermes.models.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeResponse {
    private int id;
    private String name;
    private int time;
    private int price;
    private boolean active;
    private boolean exclusive;
    private int amountOfImages;
    private LocationResponse location;
    private List<PromotionResponse> promotions;

    public TypeResponse() {
    }

    public TypeResponse(Type type) {
        this.id = type.getId();
        this.name = type.getName();
        this.time = type.getTime();
        this.price = type.getPrice();
        this.active = type.isActive();
        this.exclusive = type.isExclusive();
        this.amountOfImages = type.getAmountOfImages();
        this.location = type.getLocation().toResponse(true);
        this.promotions = new ArrayList<>();
        if (type.getPromotions() != null) {
            for (Promotion promotion: type.getPromotions()) {
                addPromotion(promotion.toResponse(true));
            }
        }
    }

    public TypeResponse(Type type, boolean simple) {
        this.id = type.getId();
        this.name = type.getName();
    }

    public void addPromotion(PromotionResponse promotionResponse) {
        this.promotions.add(promotionResponse);
    }
}
