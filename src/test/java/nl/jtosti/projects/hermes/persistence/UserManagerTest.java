package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.util.GsonProvider;
import nl.jtosti.projects.hermes.util.Util;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    private User user = GsonProvider.getGson().fromJson("{\"firstName\":\"Joost\",\"lastName\":\"Lekkerkerker\",\"email\":\"joostlek@outlook.com\",\"phoneNumber\":\"123\",\"street\":\"\",\"houseNumber\":\"1\",\"zipCode\":\"\",\"city\":\"Harmelen\",\"country\":\"The Netherlands\",\"locations\":[],\"images\":[],\"promotions\":[],\"password\":\"kek\"}", User.class);
    private UserManager userManager = new UserManager();

    @Test
    void get() {
        assertThrows(EntityNotFoundException.class, () -> {
            userManager.get(0);
        });
    }

    @Test
    void manager() {
        user = userManager.save(user);
        System.out.println(GsonProvider.getGson().toJson(user));
        assertNotNull(user.getId());
        user.setFirstName("kekkek");
        user = userManager.update(user);
        assertEquals("kekkek", userManager.get(user.getId()).getFirstName());
        assertTrue(userManager.getAll().size() > 0);
        assertNull(userManager.getUserByLogin("joostlek@outlook.com", "kekkek"));
        assertEquals("kekkek", userManager.getUserByLogin("joostlek@outlook.com", Util.toDatabase("kek")).getFirstName());
        assertTrue(userManager.delete(user));
        assertThrows(EntityNotFoundException.class, () -> {
            userManager.get(user.getId());
        });
    }

    @Test
    void delete() {
        User deleteUser = GsonProvider.getGson().fromJson("{\"id\":0, \"firstName\":\"Joost\",\"lastName\":\"Lekkerkerker\",\"email\":\"joostlek@outlook.com\",\"phoneNumber\":\"123\",\"street\":\"\",\"houseNumber\":\"1\",\"zipCode\":\"\",\"city\":\"Harmelen\",\"country\":\"The Netherlands\",\"locations\":[],\"images\":[],\"promotions\":[],\"password\":\"kek\"}", User.class);
        assertThrows(EntityNotFoundException.class, () -> {
            userManager.delete(deleteUser);
        });
    }

    @Test
    void update() {
        User updateUser = GsonProvider.getGson().fromJson("{\"id\":0, \"firstName\":\"Joost\",\"lastName\":\"Lekkerkerker\",\"email\":\"joostlek@outlook.com\",\"phoneNumber\":\"123\",\"street\":\"\",\"houseNumber\":\"1\",\"zipCode\":\"\",\"city\":\"Harmelen\",\"country\":\"The Netherlands\",\"locations\":[],\"images\":[],\"promotions\":[],\"password\":\"kek\"}", User.class);
        updateUser.setFirstName("update");
        assertThrows(EntityNotFoundException.class, () -> {
            userManager.update(updateUser);
        });
    }

}