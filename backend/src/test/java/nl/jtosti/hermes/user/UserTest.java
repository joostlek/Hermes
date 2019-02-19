package nl.jtosti.hermes.user;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.screen.Screen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        assertThat(user.getCompanies()).hasSize(0);
        assertThat(user.getImages()).hasSize(0);
    }

    @Test
    @DisplayName("Test no list constructor")
    void testNoListConstructor() {
        String firstName = "Alexandra";
        String lastName = "Petrol";
        String email = "Alexandra.petrol@asd.com";
        user = new User(firstName, lastName, email, "");
        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getCompanies()).hasSize(0);
        assertThat(user.getImages()).hasSize(0);
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        user = new User("Alex", "Jones", "alex.jones@alex.com", "");
        String firstName = "Jane";
        String lastName = "Jay";
        String email = "jane.jay@jay.com";
        Company company = new Company();
        Location location = new Location("", "", "", "", "", "", company);
        Screen screen = new Screen("", 1920, 1080, location);
        Image image = new Image("", "", screen, user);
        Set<Image> images = new HashSet<>(Collections.singletonList(image));
        Set<Company> companies = new HashSet<>(Collections.singletonList(company));

        List<String> roles = Collections.singletonList("USER");

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setImages(images);
        user.setCompanies(companies);
        user.setRoles(roles);

        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getRoles()).isEqualTo(roles);
        assertThat(user.getCompanies().contains(company)).isTrue();
        assertThat(user.getImages().contains(image)).isTrue();


        user = new User("Alex", "Jones", "alex.jones@alex.com", "");

        user.addCompany(company);
        user.addImage(image);

        assertThat(user.getCompanies().contains(company)).isTrue();
        assertThat(user.getImages().contains(image)).isTrue();
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        user = new User("Alex", "Jones", "Alex.jones@alex.com", "");
        user.setId(1L);
        User user1 = new User("Alex", "Jones", "Alex.jones@alex.com", "");
        user1.setId(1L);
        Object company = new Company();

        assertThat(user.equals(user1)).isTrue();
        assertThat(user.equals(company)).isFalse();

        user1.setEmail("JJ@J.com");
        assertThat(user.equals(user1)).isFalse();
        user1.setId(2L);
        assertThat(user.equals(user1)).isFalse();
        user1.setLastName("Jane");
        assertThat(user.equals(user1)).isFalse();
        user1.setFirstName("Jay");
        assertThat(user.equals(user1)).isFalse();
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashCode() {
        user = new User("Alex", "Jones", "Alex.jones@alex.com", "");
        assertThat(user.hashCode()).isEqualTo(user.hashCode());
    }

    @Test
    @DisplayName("Test toString")
    void testToString() {
        user = new User("Alex", "Jones", "Alex.jones@alex.com", "");
        assertThat(user.toString()).isEqualTo(String.format("<User: %s %s>", user.getFirstName(), user.getLastName()));
        user.setId(Integer.toUnsignedLong(1));
        assertThat(user.toString()).isEqualTo(String.format("<User %s: %s %s>", user.getId(), user.getFirstName(), user.getLastName()));
    }
}
