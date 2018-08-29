package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationResponseTest {
    private LocationResponse locationResponse;
    private Location location = new Location("Location A", "Street", "House number", "3481sa", "asd", "asd", new User());

    @Test
    void constructorTest() {
        locationResponse = new LocationResponse();
        assertNotNull(locationResponse);
        locationResponse = null;

        locationResponse = new LocationResponse(location);
        assertNotNull(locationResponse);
    }

    @Test
    void addType() {
    }

    @Test
    void addScreen() {
    }
}