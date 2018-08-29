package nl.jtosti.projects.hermes.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerProviderTest {

    @Test
    void getUserManager() {
        assertNotNull(ManagerProvider.getUserManager());
    }

    @Test
    void getImageManager() {
        assertNotNull(ManagerProvider.getImageManager());
    }

    @Test
    void getLocationManager() {
        assertNotNull(ManagerProvider.getLocationManager());
    }

    @Test
    void getPromotionManager() {
        assertNotNull(ManagerProvider.getPromotionManager());
    }

    @Test
    void getScreenManager() {
        assertNotNull(ManagerProvider.getScreenManager());
    }

    @Test
    void getTypeManager() {
        assertNotNull(ManagerProvider.getTypeManager());
    }
}