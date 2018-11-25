package nl.jtosti.hermes.entities.dto;

public class ImageDTO {
    private Long id;
    private String name;
    private String url;
    private BasicScreenDTO screen;
    private BasicUserDTO owner;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BasicScreenDTO getScreen() {
        return screen;
    }

    public void setScreen(BasicScreenDTO screen) {
        this.screen = screen;
    }

    public BasicUserDTO getOwner() {
        return owner;
    }

    public void setOwner(BasicUserDTO owner) {
        this.owner = owner;
    }
}
