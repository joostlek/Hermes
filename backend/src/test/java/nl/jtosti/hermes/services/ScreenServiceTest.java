package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.repositories.ScreenRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ScreenServiceTest {

    @Autowired
    private ScreenServiceInterface screenService;

    @MockBean
    private ScreenRepository screenRepository;

    private Location location;

    @Before
    public void setUp() {
        location = new Location("", new User("Alex", "Jones", "alex.jones@alex.com"));
        location.setId(1L);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGivenValidId_thenReturnUser() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        when(screenRepository.findById(1L)).thenReturn(Optional.of(screen));

        assertThat(screenService.getScreenById(1L)).isEqualTo(screen);
    }

    @Test
    public void whenGivenInvalidId_thenReturnNull() {
        assertThat(screenService.getScreenById(2L)).isNull();
    }

    @Test
    public void returnAllScreens() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        Screen screen1 = new Screen("Screen 2", 1920, 1080, location);
        when(screenRepository.findAll()).thenReturn(Arrays.asList(screen, screen1));

        List<Screen> screens = screenService.getAllScreens();

        assertThat(screens).hasSize(2);
        assertThat(screens.get(0)).isEqualTo(screen);
        assertThat(screens.get(1)).isEqualTo(screen1);
    }

    @Test
    public void whenGivenScreenId_thenReturnBoolean() {
        when(screenRepository.existsById(1L)).thenReturn(true);
        when(screenRepository.existsById(2L)).thenReturn(false);

        assertThat(screenService.exists(1L)).isTrue();
        assertThat(screenService.exists(2L)).isFalse();
    }

    @Test
    public void whenGivenLocationId_thenReturnScreens() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        Screen screen1 = new Screen("Screen 2", 1920, 1080, location);

        when(screenRepository.findAllByLocationId(1L)).thenReturn(Arrays.asList(screen, screen1));

        List<Screen> screens = screenService.getScreensByLocationId(1L);
        assertThat(screens).hasSize(2);
        assertThat(screens.get(0).getLocation().getId()).isEqualTo(1L);
        assertThat(screens.get(1).getLocation().getId()).isEqualTo(1L);
    }

    @Test
    public void testSaveScreen_thenReturnScreen() {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);
        when(screenRepository.save(any(Screen.class))).thenReturn(screen);

        Screen screen1 = new Screen("Screen 1", 1920, 1080, location);
        screen1 = screenService.save(screen1);
        assertThat(screen1.getId()).isNotNull();
    }

    @TestConfiguration
    static class ScreenServiceTestContextConfiguration {
        @Bean
        public ScreenServiceInterface screenServiceInterface() {
            return new ScreenService();
        }
    }
}
