package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.exceptions.LocationNotFoundException;
import nl.jtosti.hermes.repositories.LocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class LocationServiceTest {

    @Autowired
    private LocationServiceInterface locationService;

    @MockBean
    private LocationRepository locationRepository;

    @Before
    public void setUp() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        user.setId(1L);
        Location location = new Location("Alex coffee", user);
        location.setId(1L);
        Location location1 = new Location("Jane coffee", user);
        location1.setId(2L);
        Location location2 = new Location("Jays coffee", user);
        location1.setId(3L);

        when(locationRepository.findAllByOwnerIdOrderByIdAsc(1L)).thenReturn(Arrays.asList(location, location1));
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
        when(locationRepository.findAll()).thenReturn(Arrays.asList(location, location1, location2));
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        when(locationRepository.existsById(1L)).thenReturn(true);
        when(locationRepository.existsById(4L)).thenReturn(false);
    }

    @Test
    public void whenGivenValidId_thenReturnLocation() {
        assertThat(locationService.getLocationById(1L).getName()).isEqualTo("Alex coffee");
    }

    @Test
    public void whenGivenInvalidId_thenReturnNull() {
        try {
            locationService.getLocationById(4L);
//          The assertion below would fail, so the statement above would need to throw the exception
            assertThat(true).isFalse();
        } catch (LocationNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find location 4");
        }
    }

    @Test
    public void whenGivenUserId_thenReturnLocationList() {
        Long id = 1L;
        List<Location> locations = locationService.getLocationsByUserId(id);

        assertThat(locations).hasSize(2);
        assertThat(locations.get(0).getName()).isEqualTo("Alex coffee");
        assertThat(locations.get(1).getName()).isEqualTo("Jane coffee");
    }

    @Test
    public void whenGivenLocation_thenUpdateLocation() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        Location location = new Location("Alex coffee", user);
        Location location1 = new Location("Jane coffee", user);

        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
        when(locationRepository.save(location1)).thenReturn(location1);

        assertThat(locationService.update(location1, 1L)).isEqualTo(location1);
    }

    @Test
    public void whenInvalidUpdateId_throwException() {
        try {
            locationService.update(new Location("Alex coffee", new User("Alex", "Coffee", "alex.jones@alex.com")), 4L);
//          The assertion below would fail, so the statement above would need to throw the exception
            assertThat(true).isFalse();
        } catch (LocationNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find location 4");
        }
    }

    @Test
    public void returnAllLocations() {
        List<Location> locations = locationService.getAllLocations();

        assertThat(locations).hasSize(3);
        assertThat(locations.get(0).getName()).isEqualTo("Alex coffee");
        assertThat(locations.get(1).getName()).isEqualTo("Jane coffee");
        assertThat(locations.get(2).getName()).isEqualTo("Jays coffee");
    }

    @Test
    public void whenGivenLocationId_thenReturnBoolean() {
        assertThat(locationService.exists(1L)).isTrue();
        assertThat(locationService.exists(4L)).isFalse();
    }

    @Test
    public void whenGivenLocation_thenSaveLocation() {
        Location location = new Location("Alex Coffee", new User("Alex", "Jones", "alex.jones@alex.com"));
        location = locationService.save(location);
        assertThat(location.getId()).isNotNull();
    }

    @Test
    public void whenGivenLocationId_thenDeleteLocation() {
        locationService.delete(1L);
    }

    @TestConfiguration
    static class LocationServiceTestContextConfiguration {
        @Bean
        public LocationServiceInterface locationServiceInterface() {
            return new LocationService();
        }
    }
}
