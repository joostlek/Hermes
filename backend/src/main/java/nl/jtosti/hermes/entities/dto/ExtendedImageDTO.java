package nl.jtosti.hermes.entities.dto;

public class ExtendedImageDTO extends ImageDTO {
    private ScreenDTO screen;
    private UserDTO owner;

    public ScreenDTO getScreen() {
        return screen;
    }

    public void setScreen(ScreenDTO screen) {
        this.screen = screen;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
