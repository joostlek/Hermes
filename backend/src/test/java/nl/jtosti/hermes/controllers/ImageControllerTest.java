package nl.jtosti.hermes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.entities.Image;
import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.services.ImageServiceInterface;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
public class ImageControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ImageServiceInterface imageService;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com");
    private Location location = new Location("Alex coffee", user);
    private Screen screen = new Screen("Screen 1", 1920, 1080, location);

    @Test
    public void givenImages_whenGetImages_thenReturnJsonArray() throws Exception {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "2", screen, user);
        List<Image> images = Arrays.asList(image, image1);

        given(imageService.getAllImages()).willReturn(images);

        mvc.perform(get("/images")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(image.getName())))
                .andExpect(jsonPath("$[1].name", is(image1.getName())));
    }

    @Test
    public void givenImage_whenGetImage_thenReturnJsonObject() throws Exception {
        Image image = new Image("Image 1", "asd", screen, user);

        when(imageService.getImageById(1L)).thenReturn(image);

        mvc.perform(get("/images/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(image.getName())))
                .andExpect(jsonPath("$.url", is(image.getUrl())));
    }

    @Test
    public void whenNewUser_thenReturnSavedUser() throws Exception {
        Image image = new Image("Image 1", "asd", screen, user);

        when(imageService.save(any(Image.class))).thenReturn(image);

        mvc.perform(post("/images")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .with(user("user"))
                .content(objectMapper.writer().writeValueAsString(image)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(image.getName())))
                .andExpect(jsonPath("$.url", is(image.getUrl())));
    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedUser() throws Exception {
        Image image = new Image("Image 1", "asd", screen, user);
        Image image1 = new Image("Image 2", "asdasd", screen, user);

        when(imageService.getImageById(1L)).thenReturn(image);
        when(imageService.update(any(Image.class))).thenReturn(image1);

        mvc.perform(put("/images/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .with(user("user"))
                .content(objectMapper.writer().writeValueAsString(image1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(image1.getName())))
                .andExpect(jsonPath("$.url", is(image1.getUrl())));
    }

    @Test
    public void whenDeleteUser() throws Exception {
        mvc.perform(delete("/images/1")
                .with(csrf())
                .with(user("user"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}