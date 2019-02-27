package nl.jtosti.hermes.image.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.image.ImageServiceInterface;
import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
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
@WebMvcTest(ImageController.class)
@DisplayName("Image Controller")
@Tag("Controller")
class ImageControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ScreenServiceInterface screenService;

    @MockBean
    private UserServiceInterface userService;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ImageServiceInterface imageService;

    @MockBean
    private StorageServiceInterface storageService;

    @MockBean
    private CompanyServiceInterface companyService;

    @MockBean
    private UserAuthenticationProvider authenticationProvider;

    @MockBean
    private ScreenAuthenticationProvider screenAuthenticationProvider;

    private User user = new User("Alex", "Jones", "alex.jones@alex.com", "");

    private Company company = new Company("", "", "", "", "", "", "");

    private Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);

    private Screen screen = new Screen("Screen 1", 1920, 1080, location);

    @Test
    @DisplayName("Get all images")
    void shouldReturnAllImages_whenGetAllImages() throws Exception {
        Image image = new Image("Image 1", "3", screen, user);
        image.setId(1L);
        Image image1 = new Image("Image 2", "2", screen, user);
        image1.setId(2L);
        List<Image> images = Arrays.asList(image, image1);

        given(imageService.getAllImages()).willReturn(images);

        mvc.perform(get("/api/images")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(image.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(image.getName())))
                .andExpect(jsonPath("$[0].url", is(image.getUrl())))
                .andExpect(jsonPath("$[0].screen").doesNotExist())
                .andExpect(jsonPath("$[0].owner").doesNotExist())
                .andExpect(jsonPath("$[1].id", is(image1.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(image1.getName())))
                .andExpect(jsonPath("$[1].url", is(image1.getUrl())))
                .andExpect(jsonPath("$[1].screen").doesNotExist())
                .andExpect(jsonPath("$[1].owner").doesNotExist());
    }

//    @Test
//    @DisplayName("Get all images by user")
//    void shouldReturnUserImages_whenGetImagesByUser() throws Exception {
//        Image image = new Image("Image 1", "3", screen, user);
//        image.setId(1L);
//        Image image1 = new Image("Image 2", "2", screen, user);
//        image1.setId(2L);
//        List<Image> images = Arrays.asList(image, image1);
//
//        given(imageService.getImagesByUserId(1L)).willReturn(images);
//
//        mvc.perform(get("/users/1/images")
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(user("user")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(image.getId().intValue())))
//                .andExpect(jsonPath("$[0].name", is(image.getName())))
//                .andExpect(jsonPath("$[0].url", is(image.getUrl())))
//                .andExpect(jsonPath("$[0].screen").exists())
//                .andExpect(jsonPath("$[0].screen.name", is(screen.getName())))
//                .andExpect(jsonPath("$[0].owner").exists())
//                .andExpect(jsonPath("$[0].owner.firstName", is(user.getFirstName())))
//                .andExpect(jsonPath("$[1].id", is(image1.getId().intValue())))
//                .andExpect(jsonPath("$[1].name", is(image1.getName())))
//                .andExpect(jsonPath("$[1].url", is(image1.getUrl())))
//                .andExpect(jsonPath("$[1].screen").exists())
//                .andExpect(jsonPath("$[1].screen.name", is(screen.getName())))
//                .andExpect(jsonPath("$[1].owner").exists())
//                .andExpect(jsonPath("$[1].owner.firstName", is(user.getFirstName())));
//    }

    @Test
    @DisplayName("Get all images by screen")
    void shouldReturnScreenImages_whenGetImagesByScreen() throws Exception {
        Image image = new Image("Image 1", "3", screen, user);
        image.setId(1L);
        Image image1 = new Image("Image 2", "2", screen, user);
        image1.setId(2L);
        List<Image> images = Arrays.asList(image, image1);

        given(imageService.getImagesByScreenId(1L)).willReturn(images);

        mvc.perform(get("/api/screens/1/images")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(image.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(image.getName())))
                .andExpect(jsonPath("$[0].url", is(image.getUrl())))
                .andExpect(jsonPath("$[0].screen").exists())
                .andExpect(jsonPath("$[0].screen.name", is(screen.getName())))
                .andExpect(jsonPath("$[0].uploader").exists())
                .andExpect(jsonPath("$[0].uploader.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$[1].id", is(image1.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(image1.getName())))
                .andExpect(jsonPath("$[1].url", is(image1.getUrl())))
                .andExpect(jsonPath("$[1].screen").exists())
                .andExpect(jsonPath("$[1].screen.name", is(screen.getName())))
                .andExpect(jsonPath("$[1].uploader").exists())
                .andExpect(jsonPath("$[1].uploader.firstName", is(user.getFirstName())));
    }

//    @Test
//    @DisplayName("Get images by user ID by location ID")
//    void shouldReturnImages_whenGetImagesByUserIdAndLocationId() throws Exception {
//        Image image = new Image("Image 1", "3", screen, user);
//        image.setId(1L);
//        Image image1 = new Image("Image 2", "2", screen, user);
//        image1.setId(2L);
//        List<Image> images = Arrays.asList(image, image1);
//
//        given(imageService.getImagesByLocationIdByUserId(1L, 1L)).willReturn(images);
//
//        mvc.perform(get("/users/1/locations/1/images")
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(user("user")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(image.getId().intValue())))
//                .andExpect(jsonPath("$[0].name", is(image.getName())))
//                .andExpect(jsonPath("$[0].url", is(image.getUrl())))
//                .andExpect(jsonPath("$[0].screen").exists())
//                .andExpect(jsonPath("$[0].screen.name", is(screen.getName())))
//                .andExpect(jsonPath("$[0].owner").exists())
//                .andExpect(jsonPath("$[0].owner.firstName", is(user.getFirstName())))
//                .andExpect(jsonPath("$[1].id", is(image1.getId().intValue())))
//                .andExpect(jsonPath("$[1].name", is(image1.getName())))
//                .andExpect(jsonPath("$[1].url", is(image1.getUrl())))
//                .andExpect(jsonPath("$[1].screen").exists())
//                .andExpect(jsonPath("$[1].screen.name", is(screen.getName())))
//                .andExpect(jsonPath("$[1].owner").exists())
//                .andExpect(jsonPath("$[1].owner.firstName", is(user.getFirstName())));
//
//    }

    @Test
    @DisplayName("Get images by location ID")
    void shouldReturnImages_whenGetImagesByLocationId() throws Exception {
        Image image = new Image("Image 1", "3", screen, user);
        image.setId(1L);
        Image image1 = new Image("Image 2", "2", screen, user);
        image1.setId(2L);
        List<Image> images = Arrays.asList(image, image1);

        given(imageService.getImagesByLocationId(1L)).willReturn(images);

        mvc.perform(get("/api/locations/1/images")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(image.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(image.getName())))
                .andExpect(jsonPath("$[0].url", is(image.getUrl())))
                .andExpect(jsonPath("$[0].screen").exists())
                .andExpect(jsonPath("$[0].screen.name", is(screen.getName())))
                .andExpect(jsonPath("$[0].uploader").exists())
                .andExpect(jsonPath("$[0].uploader.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$[1].id", is(image1.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(image1.getName())))
                .andExpect(jsonPath("$[1].url", is(image1.getUrl())))
                .andExpect(jsonPath("$[1].screen").exists())
                .andExpect(jsonPath("$[1].screen.name", is(screen.getName())))
                .andExpect(jsonPath("$[1].uploader").exists())
                .andExpect(jsonPath("$[1].uploader.firstName", is(user.getFirstName())));

    }

    @Test
    @DisplayName("Get single image")
    void shouldReturnImage_whenGetSingleImage() throws Exception {
        Image image = new Image("Image 1", "asd", screen, user);
        image.setId(1L);

        when(imageService.getImageById(1L)).thenReturn(image);

        mvc.perform(get("/api/images/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(image.getId().intValue())))
                .andExpect(jsonPath("$.name", is(image.getName())))
                .andExpect(jsonPath("$.url", is(image.getUrl())))
                .andExpect(jsonPath("$.screen").exists())
                .andExpect(jsonPath("$.screen.name", is(screen.getName())))
                .andExpect(jsonPath("$.uploader").exists())
                .andExpect(jsonPath("$.uploader.firstName", is(user.getFirstName())));
    }

    @Test
    @DisplayName("Add image")
    void shouldReturnSavedImage_whenSaveImage() throws Exception {
        screen.setId(1L);
        Image image = new Image("Image 1", "asd", screen, user);
        image.setId(1L);

        when(companyService.getCompanyById(1L)).thenReturn(company);
        when(screenService.getScreenById(1L)).thenReturn(screen);
        when(imageService.save(any(Image.class))).thenReturn(image);

        mvc.perform(post("/api/companies/1/images")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .with(user("user"))
                .param("screenId", Integer.toString(screen.getId().intValue()))
                .content(objectMapper.writer().writeValueAsString(image)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(image.getId().intValue())))
                .andExpect(jsonPath("$.name", is(image.getName())))
                .andExpect(jsonPath("$.url", is(image.getUrl())))
                .andExpect(jsonPath("$.screen").exists())
                .andExpect(jsonPath("$.screen.name", is(screen.getName())))
                .andExpect(jsonPath("$.uploader").exists())
                .andExpect(jsonPath("$.uploader.firstName", is(user.getFirstName())));
    }

    @Test
    @DisplayName("Update image")
    void shouldReturnUpdatedImage_whenUpdateImage() throws Exception {
        screen.setId(1L);
        Image image = new Image("Image 1", "asd", screen, user);
        image.setId(1L);
        Image image1 = new Image("Image 2", "asdasd", screen, user);
        image1.setId(1L);

        when(imageService.getImageById(1L)).thenReturn(image);
        when(imageService.update(any(Image.class))).thenReturn(image1);

        mvc.perform(put("/api/images/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .with(user("1"))
                .content(objectMapper.writer().writeValueAsString(image1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(image1.getId().intValue())))
                .andExpect(jsonPath("$.name", is(image1.getName())))
                .andExpect(jsonPath("$.url", is(image1.getUrl())))
                .andExpect(jsonPath("$.screen").exists())
                .andExpect(jsonPath("$.screen.name", is(screen.getName())))
                .andExpect(jsonPath("$.uploader").exists())
                .andExpect(jsonPath("$.uploader.firstName", is(user.getFirstName())));
    }

    @Test
    @DisplayName("Delete image")
    void shouldDoNothing_whenDeleteImage() throws Exception {
        mvc.perform(delete("/api/images/1")
                .with(csrf())
                .with(user("user"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}