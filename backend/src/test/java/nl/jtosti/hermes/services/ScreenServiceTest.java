package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.exceptions.ScreenNotFoundException;
import nl.jtosti.hermes.repositories.ScreenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Screen Service")
@Tag("services")
class ScreenServiceTest {

    @Autowired
    private ScreenServiceInterface screenService;

    @MockBean
    private ScreenRepository screenRepository;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com");

    private Location location = new Location("Alex coffee", user);

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
        @Bean
        public ScreenServiceInterface screenServiceInterface() {
            return new ScreenService();
        }
    }
}
