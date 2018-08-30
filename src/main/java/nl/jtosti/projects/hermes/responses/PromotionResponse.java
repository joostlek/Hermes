package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.Promotion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionResponse {
    private int id;
    private String name;
    private Date startDate;
    private UserResponse owner;
    private TypeResponse type;
    private List<ImageResponse> images;

    public PromotionResponse() {
    }

    public PromotionResponse(Promotion promotion) {
        this.id = promotion.getId();
        this.name = promotion.getName();
        this.startDate = promotion.getStartDate();
        this.owner = promotion.getOwner().toResponse(true);
        this.type = promotion.getType().toResponse(true);
        this.images = new ArrayList<>();
        if (promotion.getImages() != null) {
            for (Image image : promotion.getImages()) {
                addImage(image.toResponse(true));
            }
        }
    }

    public PromotionResponse(Promotion promotion, boolean simple) {
        this.id = promotion.getId();
        this.name = promotion.getName();
    }

    public void addImage(ImageResponse imageResponse) {
        this.images.add(imageResponse);
    }
}
