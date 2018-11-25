package nl.jtosti.hermes.entities.dto;

import java.util.List;

public class ScreenDTO {
    private Long id;
    private String name;
    private List<BasicImageDTO> images;
    private BasicLocationDTO location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BasicImageDTO> getImages() {
        return images;
    }

    public void setImages(List<BasicImageDTO> images) {
        this.images = images;
    }

    public BasicLocationDTO getLocation() {
        return location;
    }

    public void setLocation(BasicLocationDTO location) {
        this.location = location;
    }
}
