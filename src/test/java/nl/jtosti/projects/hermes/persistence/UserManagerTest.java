package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.util.GsonProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    private User user = GsonProvider.getGson().fromJson("{\"id\":0,\"firstName\":\"Joost\",\"lastName\":\"Lekkerkerker\",\"email\":\"joostlek@outlook.com\",\"phoneNumber\":\"123\",\"street\":\"\",\"houseNumber\":\"1\",\"zipCode\":\"\",\"city\":\"Harmelen\",\"country\":\"The Netherlands\",\"locations\":[],\"images\":[],\"promotions\":[],\"password\":\"kek\"}", User.class);
    @Test
    void get() {
        save();
        UserManager userManager = new UserManager();
        User dbUser = userManager.get(user.getId());
        assertEquals(user, dbUser);
    }

    @Test
    void save() {
        UserManager userManager = new UserManager();
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