package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Image;

public class ImageResponse {
    private int id;
    private String name;
    private int height;
    private int width;
    private String url;
    private boolean active;
    private int time;
    private ScreenResponse screen;
    private UserResponse owner;
    private PromotionResponse promotion;

    public ImageResponse() {
    }

    public ImageResponse(Image image) {
        this.id = image.getId();
        this.name = image.getName();
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.url = image.getUrl();
        this.active = image.isActive();
        this.time = image.getTime();
        this.screen = image.getScreen().toResponse(true);
        this.owner = image.getOwner().toResponse(true);
        this.promotion = image.getPromotion().toResponse(true);
    }

    public ImageResponse(Image image, boolean simple) {
        this.id = image.getId();
        this.name = image.getName();
    }
}
