package nl.jtosti.hermes.services;

import nl.jtosti.hermes.HermesApplication;
import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenRepository;
import nl.jtosti.hermes.screen.ScreenService;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
import nl.jtosti.hermes.screen.auth.exception.ScreenIsNotToReceivePasswordException;
import nl.jtosti.hermes.screen.exception.ScreenNotFoundException;
import nl.jtosti.hermes.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HermesApplication.class)
@DisplayName("Screen Service")
@Tag("services")
class ScreenServiceTest {

    @Autowired
    private ScreenServiceInterface screenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private ScreenRepository screenRepository;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com", "");

    private Company company = new Company("", "", "", "", "", "", "");

    private Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);

    @Test
    @DisplayName("Get single screen")
    void shouldReturnScreen_whenGetSingleScreen() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        when(screenRepository.findById(1L)).thenReturn(Optional.of(screen));

        assertThat(screenService.getScreenById(1L)).isEqualTo(screen);
    }

    @Test
    @DisplayName("Get invalid screen throws exception")
    void shouldThrowException_whenGetInvalidScreen() {
        try {
            screenService.getScreenById(2L);
            assertThat(true).isFalse();
        } catch (ScreenNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find screen 2");
        }
    }

    @Test
    @DisplayName("Get all screens")
    void shouldReturnAllScreens_whenGetAllScreens() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        Screen screen1 = new Screen("Screen 2", 1920, 1080, location);
        when(screenRepository.findAll()).thenReturn(Arrays.asList(screen, screen1));

        List<Screen> screens = screenService.getAllScreens();

        assertThat(screens).hasSize(2);
        assertThat(screens.get(0)).isEqualTo(screen);
        assertThat(screens.get(1)).isEqualTo(screen1);
    }

    @Test
    @DisplayName("Screen exists")
    void shouldReturnTrue_whenScreenExists() {
        when(screenRepository.existsById(1L)).thenReturn(true);

        assertThat(screenService.exists(1L)).isTrue();
    }

    @Test
    @DisplayName("Screen doesn't exist")
    void shouldReturnTrue_whenScreenDoesNotExists() {
        when(screenRepository.existsById(2L)).thenReturn(false);

        assertThat(screenService.exists(2L)).isFalse();
    }

    @Test
    @DisplayName("Get screens by location id")
    void shouldReturnLocationScreens_whenGetScreensByLocationId() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        Screen screen1 = new Screen("Screen 2", 1920, 1080, location);

        when(screenRepository.findAllByLocationId(1L)).thenReturn(Arrays.asList(screen, screen1));

        List<Screen> screens = screenService.getScreensByLocationId(1L);
        assertThat(screens).hasSize(2);
        assertThat(screens.get(0)).isEqualTo(screen);
        assertThat(screens.get(1)).isEqualTo(screen1);
    }

    @Test
    @DisplayName("Add screen")
    void shouldReturnSavedScreen_whenSaveScreen() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        when(screenRepository.save(any(Screen.class))).thenReturn(screen);

        Screen newScreen = screenService.save(screen);
        assertThat(newScreen).isEqualTo(screen);
    }

    @Test
    @DisplayName("Update screen")
    void shouldReturnUpdatedScreen_whenUpdateScreen() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        Screen screen1 = new Screen("Screen 11", 1020, 1980, location);
        screen1.setId(1L);

        when(screenRepository.findById(1L)).thenReturn(Optional.of(screen));
        when(screenRepository.save(screen1)).thenReturn(screen1);

        assertThat(screenService.updateScreen(screen1)).isEqualTo(screen1);
    }

    @Test
    @DisplayName("Register screen")
    void shouldCreatePassword_whenRegisterScreen() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        when(screenRepository.findById(1L)).thenReturn(Optional.of(screen));

        assertThat(screen.isToReceivePassword()).isTrue();
        String password = screenService.registerScreen(1L);
        assertThat(passwordEncoder.matches(password, screen.getPassword())).isTrue();
    }

    @Test
    @DisplayName("Register already registered screen")
    void shouldThrowScreenIsNotToReceivePasswordException_whenScreenIsNotToReceivePassword() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        when(screenRepository.findById(1L)).thenReturn(Optional.of(screen));

        assertThat(screen.isToReceivePassword()).isTrue();
        String password = screenService.registerScreen(1L);
        assertThat(screen.isToReceivePassword()).isFalse();
        try {
            screenService.registerScreen(1L);
            assertThat(true).isFalse();
        } catch (ScreenIsNotToReceivePasswordException ex) {
            assertThat(ex.getMessage()).isEqualTo("Screen 1 is not set to receive password");
        }
    }

    @Test
    @DisplayName("Update invalid screen throws error")
    void shouldThrowError_whenUpdateInvalidScreen() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        try {
            screenService.updateScreen(screen);
            assertThat(true).isFalse();
        } catch (ScreenNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find screen 1");
        }
    }

    @Test
    @DisplayName("Delete screen")
    void shouldDoNothing_whenDeleteScreen() {
        screenService.deleteScreen(1L);
    }

    @TestConfiguration
    static class ScreenServiceTestContextConfiguration {

        @Autowired
        private ScreenRepository screenRepository;

        @Bean
        public ScreenServiceInterface screenServiceInterface() {
            return new ScreenService(screenRepository);
        }
    }
}
