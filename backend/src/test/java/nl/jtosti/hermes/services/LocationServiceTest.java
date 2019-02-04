//package nl.jtosti.hermes.services;
//
//import nl.jtosti.hermes.company.Company;
//import nl.jtosti.hermes.location.Location;
//import nl.jtosti.hermes.user.User;
//import nl.jtosti.hermes.location.exception.LocationNotFoundException;
//import nl.jtosti.hermes.location.LocationRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@DisplayName("Location Service")
//@Tag("services")
//class LocationServiceTest {
//
//    @Autowired
//    private LocationServiceInterface locationService;
//
//    @MockBean
//    private LocationRepository locationRepository;
//
//    private Company user = new User("Alex", "Jones", "alex.jones@alex.com");
//
//    @Test
//    @DisplayName("Get single location")
//    void shouldReturnLocation_whenGetSingleLocation() {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//
//        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
//
//        assertThat(locationService.getLocationById(1L)).isEqualTo(location);
//    }
//
//    @Test
//    @DisplayName("Get invalid user throws exception")
//    void shouldThrowException_whenGetInvalidLocation() {
//        try {
//            locationService.getLocationById(4L);
////          The assertion below would fail, so the statement above would need to throw the exception
//            assertThat(true).isFalse();
//        } catch (LocationNotFoundException ex) {
//            assertThat(ex.getMessage()).isEqualTo("Could not find location 4");
//        }
//    }
//
//    @Test
//    @DisplayName("Get locations by user id")
//    void shouldReturnUserLocations_whenGetLocationsByUserId() {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        Location location1 = new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        List<Location> locationList = Arrays.asList(location, location1);
//
//        given(locationRepository.findAllByOwnerIdOrderByIdAsc(1L)).willReturn(locationList);
//
//        List<Location> locations = locationService.getLocationsByUserId(1L);
//
//        assertThat(locations).hasSize(2);
//        assertThat(locations.get(0)).isEqualTo(location);
//        assertThat(locations.get(1)).isEqualTo(location1);
//    }
//
//    @Test
//    @DisplayName("Update location")
//    void shouldReturnUpdatedLocation_whenUpdateLocation() {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        location.setId(1L);
//        Location location1 = new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        location1.setId(1L);
//
//        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
//        when(locationRepository.save(location1)).thenReturn(location1);
//
//        assertThat(locationService.update(location1)).isEqualTo(location1);
//    }
//
//    @Test
//    @DisplayName("Update invalid user throws exception")
//    void shouldThrowException_whenUpdateInvalidLocation() {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        location.setId(4L);
//        try {
//            locationService.update(location);
////          The assertion below would fail, so the statement above would need to throw the exception
//            assertThat(true).isFalse();
//        } catch (LocationNotFoundException ex) {
//            assertThat(ex.getMessage()).isEqualTo("Could not find location 4");
//        }
//    }
//
//    @Test
//    @DisplayName("Get all locations")
//    void shouldReturnAllLocations_whenGetAllLocations() {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        location.setId(1L);
//        Location location1 = new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        location1.setId(2L);
//        List<Location> locationList = Arrays.asList(location, location1);
//        given(locationRepository.findAll()).willReturn(locationList);
//
//        List<Location> locations = locationService.getAllLocations();
//
//        assertThat(locations).hasSize(2);
//        assertThat(locations.get(0)).isEqualTo(location);
//        assertThat(locations.get(1)).isEqualTo(location1);
//    }
//
//    @Test
//    @DisplayName("Get all locations")
//    void shouldReturnAllPersonalLocations_whenGetAllPersonalLocations() {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        location.setId(1L);
//        Location location1 = new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        location1.setId(2L);
//        List<Location> locationList = Arrays.asList(location, location1);
//        given(locationRepository.findPersonalLocationsByUserId(1L)).willReturn(locationList);
//
//        List<Location> locations = locationService.getPersonalLocationsByUserId(1L);
//
//        assertThat(locations).hasSize(2);
//        assertThat(locations.get(0)).isEqualTo(location);
//        assertThat(locations.get(1)).isEqualTo(location1);
//    }
//
//    @Test
//    @DisplayName("Location exists")
//    void shouldReturnTrue_whenLocationExists() {
//        when(locationRepository.existsById(1L)).thenReturn(true);
//
//        assertThat(locationService.exists(1L)).isTrue();
//    }
//
//    @Test
//    @DisplayName("Location doesn't exist")
//    void shouldReturnFalse_whenLocationDoesNotExist() {
//        assertThat(locationService.exists(2L)).isFalse();
//    }
//
//    @Test
//    @DisplayName("Add location")
//    void shouldReturnSavedLocation_whenSaveLocation() {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", user);
//        when(locationRepository.save(any(Location.class))).thenReturn(location);
//
//        Location newLocation = locationService.save(location);
//        assertThat(newLocation).isNotNull().isEqualTo(location);
//    }
//
//    @Test
//    @DisplayName("Delete location")
//    void shouldDoNothing_whenDeleteLocation() {
//        locationService.delete(1L);
//    }
//
//    @TestConfiguration
//    static class LocationServiceTestContextConfiguration {
//        @Bean
//        public LocationServiceInterface locationServiceInterface() {
//            return new LocationService();
//        }
//    }
//}
