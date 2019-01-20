package nl.jtosti.hermes.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Java6Assertions.assertThat;

class ScreenTest {
    private Screen screen;

    private Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", new User());

    @Test
    @DisplayName("Test no args constructor")
    void testNoArgsConstructor() {
        screen = new Screen();

        assertThat(screen.getId()).isNull();
        assertThat(screen.getName()).isNull();
        assertThat(screen.getHeight()).isEqualTo(0);
        assertThat(screen.getWidth()).isEqualTo(0);
        assertThat(screen.getLocation()).isNull();
        assertThat(screen.getImages()).hasSize(0);
    }

    @Test
    @DisplayName("Test no list constructor")
    void testNoListConstructor() {
        int width = 1920;
        int height = 1080;
        String name = "Screen 1";
        screen = new Screen(name, width, height, location);

        assertThat(screen.getId()).isNull();
        assertThat(screen.getName()).isEqualTo(name);
        assertThat(screen.getHeight()).isEqualTo(height);
        assertThat(screen.getWidth()).isEqualTo(width);
        assertThat(screen.getLocation()).isEqualTo(location);
        assertThat(screen.getImages()).hasSize(0);
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        screen = new Screen("Screen 1", 1920, 1080, location, new ArrayList<>());

        Image image = new Image();

        String name = "Screen 2";
        int width = 1000;
        int height = 1000;

        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", new User());

        screen.setName(name);
        screen.setHeight(height);
        screen.setWidth(width);
        screen.setImages(Collections.singletonList(image));
        screen.setLocation(location);

        assertThat(screen.getName()).isEqualTo(name);
        assertThat(screen.getHeight()).isEqualTo(height);
        assertThat(screen.getWidth()).isEqualTo(width);
        assertThat(screen.getImages()).hasSize(1);
        assertThat(screen.getImages().get(0)).isEqualTo(image);

        screen = new Screen("Screen 1", 1920, 1080, location, new ArrayList<>());
        screen.addImage(image);

        assertThat(screen.getImages()).hasSize(1);
        assertThat(screen.getImages().get(0)).isEqualTo(image);
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        screen = new Screen("Alex Coffee", 1920, 1080, location);
        screen.setId(1L);

        Screen screen1 = new Screen("Alex Coffee", 1920, 1080, location);
        screen1.setId(1L);

        assertThat(screen.equals(screen1)).isTrue();

        Object image = new Image();

        assertThat(screen.equals(image)).isFalse();

        screen1.setName("Jane Coffee");
        assertThat(screen.equals(screen1)).isFalse();
        screen1.setWidth(1000);
        assertThat(screen.equals(screen1)).isFalse();
        screen1.setHeight(1000);
        assertThat(screen.equals(screen1)).isFalse();
        screen1.setId(2L);
        assertThat(screen.equals(screen1)).isFalse();
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashCode() {
        screen = new Screen("Screen 1", 1920, 1080, location);
        assertThat(screen.hashCode()).isEqualTo(screen.hashCode());

        Screen screen1 = new Screen();
        assertThat(screen.hashCode()).isNotEqualTo(screen1.hashCode());
    }

    @Test
    @DisplayName("Test toString")
    void testToString() {
        screen = new Screen("Screen 1", 1920, 1080, location);
        assertThat(screen.toString()).isEqualTo(String.format("<Screen: %s>", screen.getName()));
        screen.setId(1L);
        assertThat(screen.toString()).isEqualTo(String.format("<Screen %s: %s>", screen.getId(), screen.getName()));
    }

    @Test
    @DisplayName("Test setting password")
    void testSetPassword() {
        String password = "test";
        screen = new Screen("Screen 1", 1920, 1080, location);
        assertThat(screen.isToReceivePassword()).isTrue();
        screen.setPassword(password);
        assertThat(screen.isToReceivePassword()).isFalse();
        assertThat(screen.getPassword()).isNotEqualTo(password);
    }

    @Test
    @DisplayName("Convert to UserDetails")
    void shouldConvertToUserDetails() {
        String password = "test";
        screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        UserDetails userDetails = screen.toUserDetails();
        assertThat(userDetails).isInstanceOf(ApplicationScreen.class);
        assertThat(userDetails.getUsername()).isEqualTo(screen.getId().toString());
        assertThat(userDetails.isCredentialsNonExpired()).isFalse();
        screen.setPassword(password);
        userDetails = screen.toUserDetails();
        assertThat(userDetails.getPassword()).isEqualTo(screen.getPassword());
        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
        assertThat(userDetails.isEnabled()).isTrue();
        assertThat(userDetails.isAccountNonExpired()).isTrue();
        assertThat(userDetails.isAccountNonLocked()).isTrue();
        assertThat(userDetails.getAuthorities().size()).isEqualTo(1);
        assertThat(userDetails.getAuthorities().iterator().next()).isEqualTo(new SimpleGrantedAuthority("SCREEN"));
    }
}