package nl.jtosti.projects.hermes.resources;

import com.google.gson.reflect.TypeToken;
import nl.jtosti.projects.hermes.models.Roles;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.util.GsonProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserResourceTest {
    private UserResource userResource = new UserResource();
    private Type listType = new TypeToken<ArrayList<User>>() {
    }.getType();

    private User superUser = GsonProvider.getGson().fromJson("{\"id\":1,\"firstName\":\"asd\",\"middleName\":\"asd\",\"lastName\":\"asd\",\"email\":\"asd@asd.com\",\"phoneNumber\":\"1asd\",\"street\":\"asd\",\"houseNumber\":\"asd\",\"zipCode\":\"asdasd\",\"city\":\"asd\",\"country\":\"asdsa\",\"role\":\"SUPERUSER\",\"locations\":[{\"location\":{\"id\":1,\"name\":\"asd\"},\"role\":\"MANAGER\"}],\"images\":[],\"promotions\":[]}", User.class);
    private SecContext superUserContext = new SecContext(superUser, Roles.SUPERUSER, true);

    private User fakeUser = GsonProvider.getGson().fromJson("{\"id\":0,\"firstName\":\"asd\",\"middleName\":\"asd\",\"lastName\":\"asd\",\"email\":\"asd@asd.com\",\"phoneNumber\":\"1asd\",\"street\":\"asd\",\"houseNumber\":\"asd\",\"zipCode\":\"asdasd\",\"city\":\"asd\",\"country\":\"asdsa\",\"role\":\"SUPERUSER\",\"locations\":[{\"location\":{\"id\":1,\"name\":\"asd\"},\"role\":\"MANAGER\"}],\"images\":[],\"promotions\":[]}", User.class);
    private SecContext fakeUserContext = new SecContext(fakeUser, Roles.SUPERUSER, true);

    @Test
    @DisplayName("Get all users")
    void getAll() {
        List<User> users = GsonProvider.getGson().fromJson((String) userResource.getAll().getEntity(), listType);
        assert (users.size() > 0);
    }

    @Test
    @DisplayName("Get user by ID")
    void getUserById() {
        assertEquals(1, toUser(userResource.getUserById(1)).getId());
    }

    @Test
    @DisplayName("Get non existant user by ID")
    void getUserById1() {
        assertEquals(Response.Status.NOT_FOUND, userResource.getUserById(0).getStatusInfo());
    }

    @Test
    @DisplayName("Update user")
    void updateUser1() {
        superUser.setFirstName(superUser.getFirstName() + "a");
        assertEquals(Response.Status.OK, userResource.updateUser(superUserContext, superUser.getId(), GsonProvider.getGson().toJson(superUser.toResponse())).getStatusInfo());
        User testUser = ManagerProvider.getUserManager().get(superUser.getId());
        assertEquals(superUser.getFirstName(), testUser.getFirstName());
    }

    @Test
    @DisplayName("Update other user")
    void updateUser2() {
        superUser.setFirstName(superUser.getFirstName() + "b");
        assertEquals(Response.Status.UNAUTHORIZED, userResource.updateUser(superUserContext, 52, GsonProvider.getGson().toJson(superUser.toResponse())).getStatusInfo());
        User testUser = ManagerProvider.getUserManager().get(superUser.getId());
        assertNotEquals(superUser.getFirstName(), testUser.getFirstName());
    }

    @Test
    @DisplayName("Update Non-existing user")
    void updateUser3() {
        assertEquals(Response.Status.NOT_FOUND, userResource.updateUser(fakeUserContext, 0, GsonProvider.getGson().toJson(fakeUser.toResponse())).getStatusInfo());
    }

    @Test
    @DisplayName("Update user with invalid body")
    void updateUser4() {
        assertEquals(Response.Status.BAD_REQUEST, userResource.updateUser(superUserContext, 1, "{").getStatusInfo());
    }

    @Test
    @DisplayName("Update user with malformed id in body")
    void updateUser5() {
        assertEquals(Response.Status.UNAUTHORIZED, userResource.updateUser(superUserContext, 1, "{}").getStatusInfo());
    }

    @Test
    @DisplayName("Create user")
    void createUser() {
        assertEquals(Response.Status.OK, userResource.createUser("{\"firstName\":\"asd\",\"middleName\":\"asd\",\"lastName\":\"asd\",\"email\":\"asss@asd.com\",\"phoneNumber\":\"1asd\",\"street\":\"asd\",\"houseNumber\":\"asd\",\"zipCode\":\"asdasd\",\"city\":\"asd\",\"country\":\"asdsa\",\"role\":\"USER\",\"password\":\"kek\",\"locations\":[],\"images\":[],\"promotions\":[]}").getStatusInfo());
    }

    @Test
    @DisplayName("Create users with same email adress")
    void createUserConflict() {
        assertEquals(Response.Status.OK, userResource.createUser("{\"firstName\":\"asd\",\"middleName\":\"asd\",\"lastName\":\"asd\",\"email\":\"as@asd.com\",\"phoneNumber\":\"1asd\",\"street\":\"asd\",\"houseNumber\":\"asd\",\"zipCode\":\"asdasd\",\"city\":\"asd\",\"country\":\"asdsa\",\"role\":\"USER\",\"password\":\"kek\",\"locations\":[],\"images\":[],\"promotions\":[]}").getStatusInfo());
        assertEquals(Response.Status.CONFLICT, userResource.createUser("{\"firstName\":\"asd\",\"middleName\":\"asd\",\"lastName\":\"asd\",\"email\":\"as@asd.com\",\"phoneNumber\":\"1asd\",\"street\":\"asd\",\"houseNumber\":\"asd\",\"zipCode\":\"asdasd\",\"city\":\"asd\",\"country\":\"asdsa\",\"role\":\"USER\",\"password\":\"kek\",\"locations\":[],\"images\":[],\"promotions\":[]}").getStatusInfo());
    }

    @Test
    @DisplayName("Create user invalid syntax")
    void createUser1() {
        assertEquals(Response.Status.BAD_REQUEST, userResource.createUser("{").getStatusInfo());
    }

    @Test
    @DisplayName("Delete other user")
    void deleteUser1() {
        assertEquals(Response.Status.UNAUTHORIZED, userResource.deleteUser(fakeUserContext, 1).getStatusInfo());
    }

    @Test
    @DisplayName("Delete other user")
    void deleteUser2() {
        assertEquals(Response.Status.NOT_FOUND, userResource.deleteUser(fakeUserContext, 0).getStatusInfo());
    }

    @Test
    @DisplayName("Delete other user")
    void deleteUser3() {
        assertEquals(Response.Status.OK, userResource.deleteUser(superUserContext, 1).getStatusInfo());
    }

    private User toUser(Response response) {
        return GsonProvider.getGson().fromJson((String) response.getEntity(), User.class);
    }
}