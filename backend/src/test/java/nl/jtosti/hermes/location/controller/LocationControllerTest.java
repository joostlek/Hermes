package nl.jtosti.hermes.location.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.security.screen.ScreenAuthenticationProvider;
import nl.jtosti.hermes.security.user.UserAuthenticationProvider;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
@DisplayName("Location Controller")
@Tag("Controller")
class LocationControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LocationServiceInterface locationService;

    @MockBean
    private UserServiceInterface userService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private StorageServiceInterface storageService;

    @MockBean
    private UserAuthenticationProvider authenticationProvider;

    @MockBean
    private ScreenAuthenticationProvider screenAuthenticationProvider;

    @MockBean
    private CompanyServiceInterface companyService;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com", "");

    private Company company = new Company("", "", "", "", "", "", "");

    @Test
    @DisplayName("Get all locations")
    void shouldReturnLocations_whenGetAllLocations() throws Exception {
        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
        Location location1 = new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
        List<Location> locations = Arrays.asList(location, location1);

        given(locationService.getAllLocations()).willReturn(locations);

        mvc.perform(get("/api/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(location.getName())))
                .andExpect(jsonPath("$[1].name", is(location1.getName())));
    }

//    @Test
//    @DisplayName("Get all locations from an user")
//    void shouldReturnOwnLocations_whenGetLocationsByUserId() throws Exception {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
//        location.setId(1L);
//        Location location1 = new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
//        location1.setId(2L);
//        List<Location> locations = Arrays.asList(location, location1);
//
//        given(locationService.getLocationsByUserId(1L)).willReturn(locations);
//
//        mvc.perform(get("/users/1/locations")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .with(user("user")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(location.getId().intValue())))
//                .andExpect(jsonPath("$[0].name", is(location.getName())))
//                .andExpect(jsonPath("$[0].images").doesNotExist())
//                .andExpect(jsonPath("$[0].owner").doesNotExist())
//                .andExpect(jsonPath("$[1].id", is(location1.getId().intValue())))
//                .andExpect(jsonPath("$[1].name", is(location1.getName())))
//                .andExpect(jsonPath("$[1].images").doesNotExist())
//                .andExpect(jsonPath("$[1].owner").doesNotExist());
//
//    }

//    @Test
//    @DisplayName("Get all personal locations from an user")
//    void shouldReturnOwnLocations_whenGetPersonalLocationsByUserId() throws Exception {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
//        location.setId(1L);
//        Location location1 = new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
//        location1.setId(2L);
//        List<Location> locations = Arrays.asList(location, location1);
//
//        given(locationService.getPersonalLocationsByUserId(1L)).willReturn(locations);
//
//        mvc.perform(get("/users/1/personal-locations")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .with(user("user")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(location.getId().intValue())))
//                .andExpect(jsonPath("$[0].name", is(location.getName())))
//                .andExpect(jsonPath("$[0].images").doesNotExist())
//                .andExpect(jsonPath("$[0].owner").doesNotExist())
//                .andExpect(jsonPath("$[1].id", is(location1.getId().intValue())))
//                .andExpect(jsonPath("$[1].name", is(location1.getName())))
//                .andExpect(jsonPath("$[1].images").doesNotExist())
//                .andExpect(jsonPath("$[1].owner").doesNotExist());
//
//    }

    @Test
    @DisplayName("Get single location")
    void shouldReturnSingleLocation_whenGetLocation() throws Exception {
        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);

        given(locationService.getLocationById(1L)).willReturn(location);

        mvc.perform(get("/api/locations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location.getName())));
    }

    @Test
    @DisplayName("Add location")
    void shouldReturnSavedLocation_whenSaveLocation() throws Exception {
        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
        user.setId(1L);

        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(locationService.save(any(Location.class))).thenReturn(location);

        mvc.perform(post("/api/companies/1/locations")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("alex.jones@alex.com"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(location)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(location.getName())));
    }

//    @Test
//    @DisplayName("Add location")
//    void shouldThrowNotIdentifiedAsUserException_whenAddLocationWithDifferentUsername() throws Exception {
//        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);
//        user.setId(1L);
//
//        when(companyService.getCompanyById(1L)).thenReturn(company);
//        when(locationService.save(any(Location.class))).thenReturn(location);
//
//        mvc.perform(post("/companies/1/locations")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .with(user("aleex.jones@alex.com"))
//                .with(csrf())
//                .content(objectMapper.writer().writeValueAsString(location)))
//                .andExpect(status().isForbidden());
//    }

    @Test
    @DisplayName("Update location")
    void shouldReturnUpdatedLocation_whenUpdateLocation() throws Exception {
        Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);

        when(locationService.update(any(Location.class))).thenReturn(location);

        mvc.perform(put("/api/locations/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(location)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location.getName())));
    }

    @Test
    @DisplayName("Delete location")
    void shouldDoNothing_whenDeleteLocation() throws Exception {
        mvc.perform(delete("/api/locations/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}