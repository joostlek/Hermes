package nl.jtosti.hermes.screen.auth;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
import nl.jtosti.hermes.screen.exception.ScreenNotFoundException;
import nl.jtosti.hermes.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ScreenLoginService.class)
@DisplayName("Screen login service")
@Tag("services")
class ScreenLoginServiceTest {

    @Qualifier("screenLoginService")
    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private ScreenServiceInterface screenService;

    private User user = new User("Alex", "Jones", "Alex.jones@alex.com", "");

    private Company company = new Company("", "", "", "", "", "", "");

    private Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);

    @Test
    void shouldReturnUserDetails_whenGetScreenById() {
        Screen screen = new Screen("Scherm 1", 1920, 1080, location);
        screen.setId(1L);

        when(screenService.getScreenById(1L)).thenReturn(screen);

        UserDetails userDetails = userDetailsService.loadUserByUsername("1");
        assertThat(userDetails).isInstanceOf(ApplicationScreen.class);
        assertThat(userDetails.getUsername()).isEqualTo(screen.getId().toString());
        assertThat(userDetails.isCredentialsNonExpired()).isFalse();
        screen.setPassword("password");
        userDetails = userDetailsService.loadUserByUsername("1");
        assertThat(userDetails.getPassword()).isEqualTo(screen.getPassword());
        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
        assertThat(userDetails.isEnabled()).isTrue();
        assertThat(userDetails.isAccountNonExpired()).isTrue();
        assertThat(userDetails.isAccountNonLocked()).isTrue();
        assertThat(userDetails.getAuthorities().size()).isEqualTo(1);
        assertThat(userDetails.getAuthorities().iterator().next()).isEqualTo(new SimpleGrantedAuthority("SCREEN"));
    }

    @Test
    void shouldThrowScreenNotFoundException_whenGetUnknownScreenId() {
        when(screenService.getScreenById(2L)).thenThrow(new ScreenNotFoundException(2L));

        try {
            userDetailsService.loadUserByUsername("2");
            assertThat(true).isFalse();
        } catch (ScreenNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find screen 2");
        }
    }


}