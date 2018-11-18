package nl.jtosti.hermes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.services.LocationServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
public class LocationControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LocationServiceInterface locationServiceInterface;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com");

    @Test
    public void givenLocations_whenGetLocations_thenReturnJsonArray() throws Exception {
        Location location = new Location("Alex coffee", user);
        Location location1 = new Location("Jane coffee", user);
        List<Location> locations = Arrays.asList(location, location1);
        given(locationServiceInterface.getAllLocations()).willReturn(locations);

        mvc.perform(get("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(location.getName())))
                .andExpect(jsonPath("$[1].name", is(location1.getName())));
    }

    @Test
    public void givenLocation_whenGetLocation_thenReturnJsonObject() throws Exception {
        Location location = new Location("Alex coffee", user);
        given(locationServiceInterface.getLocationById(1L)).willReturn(location);

        mvc.perform(get("/locations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location.getName())));
    }

    @Test
    public void whenNewLocation_thenSaveUser() throws Exception {
        Location location = new Location("Alex coffee", user);
        when(locationServiceInterface.save(any(Location.class))).thenReturn(location);

        mvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(location)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location.getName())));
    }

    @Test
    public void whenUpdateLocation_thenReturnUpdatedLocation() throws Exception {
        Location location = new Location("Alex coffee", user);
        when(locationServiceInterface.update(any(Location.class), eq(1L))).thenReturn(location);

        mvc.perform(put("/locations/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user("user"))
                .with(csrf())
                .content(objectMapper.writer().writeValueAsString(location)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location.getName())));
    }

    @Test
    public void whenDeleteLocation() throws Exception {
        mvc.perform(delete("/locations/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}