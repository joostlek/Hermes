package nl.jtosti.hermes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.services.ScreenServiceInterface;
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
import static org.hamcrest.Matchers.hasSize;
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
@WebMvcTest(ScreenController.class)
public class ScreenControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScreenServiceInterface screenService;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com");

    private Location location = new Location("Alex Coffee", user);

    @Test
    public void givenScreens_whenGetScreens_thenReturnJsonArray() throws Exception {
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
                .andExpect(jsonPath("$[1].name", is(screen1.getName())));
    }

    @Test
    public void givenScreen_whenGetScreen_thenReturnJsonObject() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        when(screenService.getScreenById(1L)).thenReturn(screen);

        mvc.perform(get("/screens/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(screen.getName())));
    }

    @Test
    public void whenNewScreen_thenSaveScreen() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        when(screenService.save(any(Screen.class))).thenReturn(screen);

        mvc.perform(post("/screens")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(screen))
                .with(csrf())
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(screen.getName())));
    }

    @Test
    public void whenUpdateScreen_thenReturnUpdatedScreen() throws Exception {
        Screen screen = new Screen("Screen 1", 1920, 1080, location);
        when(screenService.updateScreen(any(Screen.class), eq(1L))).thenReturn(screen);

        mvc.perform(put("/screens/1")
                .with(csrf())
                .with(user("user"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(screen)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(screen.getName())));
    }

    @Test
    public void whenDeleteScreen() throws Exception {
        mvc.perform(delete("/screens/1")
                .with(user("user"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}