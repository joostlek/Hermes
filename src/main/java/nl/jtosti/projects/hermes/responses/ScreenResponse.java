package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.Screen;

import java.util.ArrayList;
import java.util.List;

public class ScreenResponse {
    private int id;
    private String name;
    private int height;
    private int width;
    private boolean allowAds;
    private LocationResponse location;
    private List<ImageResponse> images;

    public ScreenResponse() {
    }

    public ScreenResponse(Screen screen) {
        this.id = screen.getId();
        this.name = screen.getName();
        this.height = screen.getHeight();
        this.width = screen.getWidth();
        this.allowAds = screen.isAllowAds();
        this.location = screen.getLocation().toResponse(true);
        this.images = new ArrayList<>();
        if (screen.getImages() != null) {
            for (Image image : screen.getImages()) {
                addImage(image.toResponse(true));
            }
        }
    }

    public ScreenResponse(Screen screen, boolean simple) {
        this.id = screen.getId();
        this.name = screen.getName();
    }

    public void addImage(ImageResponse imageResponse) {
        this.images.add(imageResponse);
    }
}
