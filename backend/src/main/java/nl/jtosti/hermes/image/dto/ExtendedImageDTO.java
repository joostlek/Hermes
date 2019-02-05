package nl.jtosti.hermes.image.dto;

import nl.jtosti.hermes.screen.dto.ScreenDTO;
import nl.jtosti.hermes.user.dto.UserDTO;

public class ExtendedImageDTO extends ImageDTO {
    private ScreenDTO screen;
    private UserDTO uploader;

    public ScreenDTO getScreen() {
        return screen;
    }

    public void setScreen(ScreenDTO screen) {
        this.screen = screen;
    }

    public UserDTO getUploader() {
        return uploader;
    }

    public void setUploader(UserDTO uploader) {
        this.uploader = uploader;
    }
}
