package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.Promotion;
import nl.jtosti.projects.hermes.models.Screen;
import nl.jtosti.projects.hermes.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageManagerTest {
    private int imageId;
    @Test
    @DisplayName("Persist image")
    void save() {
        Image image = new Image("", 1080, 1920, "", true, 1, new Screen(), new User(), new Promotion());
        ImageManager imageManager = new ImageManager();
        image = imageManager.save(image);
        imageId = image.getId();
        assertNotNull(image.getId());
    }

    @Test
    @DisplayName("Get image")
    void get() {
        save();
        ImageManager imageManager = new ImageManager();
        Image image = imageManager.get(imageId);
        assertNotNull(image);
    }
}