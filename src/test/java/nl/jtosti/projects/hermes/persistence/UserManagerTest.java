package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.util.GsonProvider;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    private User user = GsonProvider.getGson().fromJson("{\"id\":0,\"firstName\":\"Joost\",\"lastName\":\"Lekkerkerker\",\"email\":\"joostlek@outlook.com\",\"phoneNumber\":\"123\",\"street\":\"\",\"houseNumber\":\"1\",\"zipCode\":\"\",\"city\":\"Harmelen\",\"country\":\"The Netherlands\",\"locations\":[],\"images\":[],\"promotions\":[],\"password\":\"kek\"}", User.class);
    private UserManager userManager = new UserManager();

    @Test
    void get() {
        assertEquals("Joost", userManager.get(1402).getFirstName());
        assertThrows(EntityNotFoundException.class, () -> {
            userManager.get(0);
        });
    }

    @Test
    void save() {
        user = userManager.save(user);
        assertNotNull(user.getId());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}