package nl.jtosti.hermes.screen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
import nl.jtosti.hermes.security.screen.ScreenAuthenticationProvider;
import nl.jtosti.hermes.security.user.UserAuthenticationProvider;
import nl.jtosti.hermes.user.User;
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
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScreenController.class)
@DisplayName("Screen Controller")
@Tag("Controller")
class ScreenControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScreenServiceInterface screenService;

    @MockBean
    private LocationServiceInterface locationService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private StorageServiceInterface storageService;

    @MockBean
    private UserAuthenticationProvider authenticationProvider;

    @MockBean
    private ScreenAuthenticationProvider screenAuthenticationProvider;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com", "");

    private Company company = new Company("", "", "", "", "", "", "");

    private Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);

    @Test
    @DisplayName("Get all screens")
    void shouldReturnAllScreens_whenGetAllScreens() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        Screen screen1 = new Screen("Screen 2", 1920, 1080, location);

        List<Screen> screens = Arrays.asList(screen, screen1);

        given(screenService.getAllScreens()).willReturn(screens);

        mvc.perform(get("/screens")
                .with(user("user"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(screen.getName())))
                .andExpect(jsonPath("$[0].width", is(screen.getWidth())))
                .andExpect(jsonPath("$[0].height", is(screen.getHeight())))
                .andExpect(jsonPath("$[0].location").doesNotExist())
                .andExpect(jsonPath("$[1].name", is(screen1.getName())))
                .andExpect(jsonPath("$[1].width", is(screen.getWidth())))
                .andExpect(jsonPath("$[1].height", is(screen.getHeight())))
                .andExpect(jsonPath("$[1].location").doesNotExist());
    }

    @Test
    @DisplayName("Get all screens by location")
    void shouldReturnScreens_whenGetAllScreensByLocationId() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        Screen screen1 = new Screen("Screen 2", 1920, 1080, location);

        List<Screen> screens = Arrays.asList(screen, screen1);

        given(screenService.getScreensByLocationId(1L)).willReturn(screens);

        mvc.perform(get("/locations/1/screens")
                .with(user("user"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(screen.getName())))
                .andExpect(jsonPath("$[0].width", is(screen.getWidth())))
                .andExpect(jsonPath("$[0].height", is(screen.getHeight())))
                .andExpect(jsonPath("$[0].location").doesNotExist())
                .andExpect(jsonPath("$[1].name", is(screen1.getName())))
                .andExpect(jsonPath("$[1].width", is(screen.getWidth())))
                .andExpect(jsonPath("$[1].height", is(screen.getHeight())))
                .andExpect(jsonPath("$[1].location").doesNotExist());
    }

    @Test
    @DisplayName("Get single screen")
    void shouldReturnScreen_whenGetSingleScreen() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);

        when(screenService.getScreenById(1L)).thenReturn(screen);

        mvc.perform(get("/screens/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(screen.getId().intValue())))
                .andExpect(jsonPath("$.name", is(screen.getName())))
                .andExpect(jsonPath("$.width", is(screen.getWidth())))
                .andExpect(jsonPath("$.height", is(screen.getHeight())))
                .andExpect(jsonPath("$.location").exists())
                .andExpect(jsonPath("$.location.id", is(location.getId())))
                .andExpect(jsonPath("$.location.name", is(location.getName())))
                .andExpect(jsonPath("$.location.screens").doesNotExist())
                .andExpect(jsonPath("$.location.owner").doesNotExist());
    }

    @Test
    @DisplayName("Add screen")
    void shouldReturnSavedScreen_whenSaveScreen() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);

        when(locationService.getLocationById(1L)).thenReturn(location);
        when(screenService.save(any(Screen.class))).thenReturn(screen);

        mvc.perform(post("/locations/1/screens")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(screen))
                .with(csrf())
                .with(user("user")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(screen.getId().intValue())))
                .andExpect(jsonPath("$.name", is(screen.getName())))
                .andExpect(jsonPath("$.width", is(screen.getWidth())))
                .andExpect(jsonPath("$.height", is(screen.getHeight())));
    }

    @Test
    @DisplayName("Update screen")
    void shouldReturnUpdatedScreen_whenUpdateScreen() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);

        when(screenService.updateScreen(any(Screen.class))).thenReturn(screen);

        mvc.perform(put("/screens/1")
                .with(csrf())
                .with(user("user"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(screen)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(screen.getId().intValue())))
                .andExpect(jsonPath("$.name", is(screen.getName())))
                .andExpect(jsonPath("$.width", is(screen.getWidth())))
                .andExpect(jsonPath("$.height", is(screen.getHeight())));
    }

    @Test
    @DisplayName("Delete screen")
    void shouldDoNothing_whenDeleteScreen() throws Exception {
        mvc.perform(delete("/screens/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}