package nl.jtosti.hermes.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DisplayName("User entity")
class UserTest {
    private User user;

    @Test
    @DisplayName("Test no args constructor")
    void testNoArgsConstructor() {
        user = new User();
        assertThat(user.getFirstName()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getLocations()).hasSize(0);
        assertThat(user.getImages()).hasSize(0);
    }

    @Test
    @DisplayName("Test no list constructor")
    void testNoListConstructor() {
        String firstName = "Alexandra";
        String lastName = "Petrol";
        String email = "Alexandra.petrol@asd.com";
        user = new User(firstName, lastName, email);
        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getLocations()).hasSize(0);
        assertThat(user.getImages()).hasSize(0);
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        user = new User("Alex", "Jones", "alex.jones@alex.com", new ArrayList<>(), new ArrayList<>());
        String firstName = "Jane";
        String lastName = "Jay";
        String email = "jane.jay@jay.com";
        Image image = new Image();
        Location location = new Location();
        List<Image> images = Collections.singletonList(image);
        List<Location> locations = Collections.singletonList(location);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setImages(images);
        user.setLocations(locations);

        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getLocations().get(0)).isEqualTo(location);
        assertThat(user.getImages().get(0)).isEqualTo(image);


        user = new User("Alex", "Jones", "alex.jones@alex.com", new ArrayList<>(), new ArrayList<>());

        user.addLocation(location);
        user.addImage(image);

        assertThat(user.getLocations().get(0)).isEqualTo(location);
        assertThat(user.getImages().get(0)).isEqualTo(image);
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        user = new User("Alex", "Jones", "Alex.jones@alex.com");
        user.setId(Integer.toUnsignedLong(1));
        User user1 = new User("Alex", "Jones", "Alex.jones@alex.com");
        user1.setId(Integer.toUnsignedLong(1));
        Object image = new Image();

        assertThat(user.equals(user1)).isTrue();
        assertThat(user.equals(image)).isFalse();

        user1.setEmail("JJ@J.com");
        assertThat(user.equals(user1)).isFalse();
        user1.setId(Integer.toUnsignedLong(2));
        assertThat(user.equals(user1)).isFalse();
        user1.setLastName("Jane");
        assertThat(user.equals(user1)).isFalse();
        user1.setFirstName("Jay");
        assertThat(user.equals(user1)).isFalse();
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashCode() {
        user = new User("Alex", "Jones", "Alex.jones@alex.com");
        assertThat(user.hashCode()).isEqualTo(user.hashCode());
    }

    @Test
    @DisplayName("Test toString")
    void testToString() {
        user = new User("Alex", "Jones", "Alex.jones@alex.com");
        assertThat(user.toString()).isEqualTo(String.format("<User: %s %s>", user.getFirstName(), user.getLastName()));
        user.setId(Integer.toUnsignedLong(1));
        assertThat(user.toString()).isEqualTo(String.format("<User %s: %s %s>", user.getId(), user.getFirstName(), user.getLastName()));
    }
}
