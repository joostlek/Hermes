package nl.jtosti.hermes.entities;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Java6Assertions.assertThat;

class LocationTest {
    private Location location;

    private User user = new User("Alex", "Jones", "Alex.jones@jones.com", new ArrayList<>(), new ArrayList<>());

    @Test
    @DisplayName("Test no args constructor")
    void testNoArgsConstructor() {
        location = new Location();
        assertThat(location.getId()).isNull();
        assertThat(location.getName()).isNull();
        assertThat(location.getOwner()).isNull();
        assertThat(location.getStreet()).isNull();
        assertThat(location.getHouseNumber()).isNull();
        assertThat(location.getZipCode()).isNull();
        assertThat(location.getCity()).isNull();
        assertThat(location.getCountry()).isNull();
        assertThat(location.getScreens()).hasSize(0);
    }

    @Test
    @DisplayName("Test no list constructor")
    void testNoListConstructor() {
        String name = "Alex Coffee";
        location = new Location(name, "Alexstreet", "1", "1234AB", "Coffee", "land", user);

        assertThat(location.getId()).isNull();
        assertThat(location.getName()).isEqualTo(name);
        assertThat(location.getStreet()).isEqualTo(location.getStreet());
        assertThat(location.getHouseNumber()).isEqualTo(location.getHouseNumber());
        assertThat(location.getZipCode()).isEqualTo(location.getZipCode());
        assertThat(location.getCity()).isEqualTo(location.getCity());
        assertThat(location.getCountry()).isEqualTo(location.getCountry());
        assertThat(location.getScreens()).hasSize(0);
        assertThat(location.getOwner()).isEqualTo(user);
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        location = new Location("Alex Coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", new ArrayList<>(), user);
        Screen screen = new Screen();
        String name = "Jane Coffee";

        location.setName(name);
        location.setOwner(user);
        location.setScreens(Collections.singletonList(screen));

        assertThat(location.getOwner()).isEqualTo(user);
        assertThat(location.getScreens().get(0)).isEqualTo(screen);
        assertThat(location.getName()).isEqualTo(name);

        location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
        location.addScreen(screen);

        assertThat(location.getScreens().get(0)).isEqualTo(screen);
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
        location.setId(1L);

        Location location1 = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
        location1.setId(1L);

        assertThat(location.equals(location1)).isTrue();

        Object image = new Image();

        assertThat(location.equals(image)).isFalse();

        location1.setName("Jane Coffee");
        assertThat(location.equals(location1)).isFalse();
        location1.setId(2L);
        assertThat(location.equals(location1)).isFalse();
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashCode() {
        location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);

        assertThat(location.hashCode()).isEqualTo(location.hashCode());
    }

    @Test
    @DisplayName("Test toString")
    void testToString() {
        location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
        assertThat(location.toString()).isEqualTo(String.format("<Location: %s>", location.getName()));
        location.setId(1L);
        assertThat(location.toString()).isEqualTo(String.format("<Location %s: %s>", location.getId(), location.getName()));
    }
}