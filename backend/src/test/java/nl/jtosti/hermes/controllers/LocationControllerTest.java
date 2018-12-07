package nl.jtosti.hermes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.exceptions.UserNotFoundException;
import nl.jtosti.hermes.services.LocationServiceInterface;
import nl.jtosti.hermes.services.StorageServiceInterface;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
    private StorageServiceInterface storageService;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com");

    @Test
    @DisplayName("Get all locations")
    void shouldReturnLocations_whenGetAllLocations() throws Exception {
        Location location = new Location("Alex coffee", user);
        Location location1 = new Location("Jane coffee", user);
        List<Location> locations = Arrays.asList(location, location1);

        given(locationService.getAllLocations()).willReturn(locations);

        mvc.perform(get("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(location.getName())))
                .andExpect(jsonPath("$[1].name", is(location1.getName())));
    }

    @Test
    @DisplayName("Get all locations from an user")
    void shouldReturnOwnLocations_whenGetLocationsByUserId() throws Exception {
        Location location = new Location("Alex coffee", user);
        location.setId(1L);
        Location location1 = new Location("Jane coffee", user);
        location1.setId(2L);
        List<Location> locations = Arrays.asList(location, location1);

        given(locationService.getLocationsByUserId(1L)).willReturn(locations);

        mvc.perform(get("/users/1/locations")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(location.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(location.getName())))
                .andExpect(jsonPath("$[0].images").doesNotExist())
                .andExpect(jsonPath("$[0].owner").doesNotExist())
                .andExpect(jsonPath("$[1].id", is(location1.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(location1.getName())))
                .andExpect(jsonPath("$[1].images").doesNotExist())
                .andExpect(jsonPath("$[1].owner").doesNotExist());

    }

    @Test
    @DisplayName("Get single location")
    void shouldReturnSingleLocation_whenGetLocation() throws Exception {
        Location location = new Location("Alex coffee", user);

        given(locationService.getLocationById(1L)).willReturn(location);

        mvc.perform(get("/locations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location.getName())))
                .andExpect(jsonPath("$.owner.id", is(location.getOwner().getId())))
                .andExpect(jsonPath("$.owner.firstName", is(location.getOwner().getFirstName())))
                .andExpect(jsonPath("$.owner.lastName", is(location.getOwner().getLastName())))
                .andExpect(jsonPath("$.owner.email", is(location.getOwner().getEmail())));
    }

    @Test
    @DisplayName("Add location to non-existant user")
    void shouldReturnNotFound_whenSaveLocationWithInvalidOwner() throws Exception {
        Location location = new Location("Alex coffee", user);

        when(userService.getUserById(333L)).thenThrow(UserNotFoundException.class);

        mvc.perform(post("/users/333/locations")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(location)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Add location")
    void shouldReturnSavedLocation_whenSaveLocation() throws Exception {
        Location location = new Location("Alex coffee", user);

        when(userService.getUserById(1L)).thenReturn(user);
        when(locationService.save(any(Location.class))).thenReturn(location);

        mvc.perform(post("/users/1/locations")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(location)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(location.getName())))
                .andExpect(jsonPath("$.owner.id", is(location.getOwner().getId())))
                .andExpect(jsonPath("$.owner.firstName", is(location.getOwner().getFirstName())))
                .andExpect(jsonPath("$.owner.lastName", is(location.getOwner().getLastName())))
                .andExpect(jsonPath("$.owner.email", is(location.getOwner().getEmail())));
    }

    @Test
    @DisplayName("Update location")
    void shouldReturnUpdatedLocation_whenUpdateLocation() throws Exception {
        Location location = new Location("Alex coffee", user);

        when(locationService.update(any(Location.class))).thenReturn(location);

        mvc.perform(put("/locations/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(location)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location.getName())))
                .andExpect(jsonPath("$.owner.id", is(location.getOwner().getId())))
                .andExpect(jsonPath("$.owner.firstName", is(location.getOwner().getFirstName())))
                .andExpect(jsonPath("$.owner.lastName", is(location.getOwner().getLastName())))
                .andExpect(jsonPath("$.owner.email", is(location.getOwner().getEmail())));
    }

    @Test
    @DisplayName("Delete location")
    void shouldDoNothing_whenDeleteLocation() throws Exception {
        mvc.perform(delete("/locations/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}