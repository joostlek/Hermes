package nl.jtosti.hermes.entities;


import nl.jtosti.hermes.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ImageTest {
    @Test
    public void testDate() {
        Image image = new Image();
        assertNull(image.getCreated());
    }
}