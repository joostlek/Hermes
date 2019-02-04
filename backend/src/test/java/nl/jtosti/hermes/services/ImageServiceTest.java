package nl.jtosti.hermes.services;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.image.ImageRepository;
import nl.jtosti.hermes.image.ImageService;
import nl.jtosti.hermes.image.ImageServiceInterface;
import nl.jtosti.hermes.image.exception.ImageNotFoundException;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Image Service")
@Tag("services")
class ImageServiceTest {

    @Autowired
    private ImageServiceInterface imageService;

    @MockBean
    private ImageRepository imageRepository;

    private User user = new User("Alex", "jones", "alex.jones@alex.com", "");

    private Company company = new Company("", "", "", "", "", "", "");

    private Location location = new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company);

    private Screen screen = new Screen("Screen 1", 1920, 1080, location);

    @Test
    @DisplayName("Get single image")
    void shouldReturnImage_whenGetSingleImage() {
        Image image = new Image("Image 1", "url", screen, user);
        image.setId(1L);

        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        assertThat(imageService.getImageById(1L)).isEqualTo(image);
    }

    @Test
    @DisplayName("Get invalid user throws exception")
    void shouldThrowException_whenGetInvalidImage() {
        try {
            imageService.getImageById(2L);
            assertThat(true).isFalse();
        } catch (ImageNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find image 2");
        }
    }

    @Test
    @DisplayName("Get all images")
    void shouldReturnAllImages_whenGetAllImages() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "", screen, user);

        when(imageRepository.findAll()).thenReturn(Arrays.asList(image, image1));

        List<Image> images = imageService.getAllImages();

        assertThat(images).hasSize(2);
        assertThat(images.get(0)).isEqualTo(image);
        assertThat(images.get(1)).isEqualTo(image1);
    }

    @Test
    @DisplayName("Get all images by location id")
    void shouldReturnAllImages_whenGetAllImagesByLocationId() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "", screen, user);

        when(imageRepository.findAllByScreenLocationId(1L)).thenReturn(Arrays.asList(image, image1));

        List<Image> images = imageService.getImagesByLocationId(1L);

        assertThat(images).hasSize(2);
        assertThat(images.get(0)).isEqualTo(image);
        assertThat(images.get(1)).isEqualTo(image1);
    }

    @Test
    @DisplayName("Get all images by user id by location id")
    void shouldReturnAllCompanyImages_whenGetAllImagesByCompanyId() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "", screen, user);

        when(imageRepository.findAllByCompanyId(1L)).thenReturn(Arrays.asList(image, image1));

        List<Image> images = imageService.getImagesByCompanyId(1L);

        assertThat(images).hasSize(2);
        assertThat(images.get(0)).isEqualTo(image);
        assertThat(images.get(1)).isEqualTo(image1);
    }

    @Test
    @DisplayName("Get all images by screen id")
    void shouldReturnScreenImages_whenGetAllImagesByScreenId() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "", screen, user);

        when(imageRepository.findAllByScreenId(1L)).thenReturn(Arrays.asList(image, image1));

        List<Image> images = imageService.getImagesByScreenId(1L);

        assertThat(images).hasSize(2);
        assertThat(images.get(0)).isEqualTo(image);
        assertThat(images.get(1)).isEqualTo(image1);
    }

    @Test
    @DisplayName("Image exists")
    void shouldReturnTrue_whenImageExists() {
        when(imageRepository.existsById(1L)).thenReturn(true);

        assertThat(imageService.exists(1L)).isTrue();
    }

    @Test
    @DisplayName("Image doesn't exist")
    void shouldReturnFalse_whenImageDoesNotExist() {
        assertThat(imageService.exists(2L)).isFalse();
    }

    @Test
    @DisplayName("Add image")
    void shouldReturnSavedImage_whenSaveImage() {
        Image image = new Image("Image 1", "", screen, user);
        image.setId(1L);

        when(imageRepository.save(any(Image.class))).thenReturn(image);

        Image newImage = imageService.save(image);
        assertThat(newImage).isEqualTo(image);
    }

    @Test
    @DisplayName("Update image")
    void shouldReturnUpdatedImage_whenUpdateImage() {
        Image image = new Image("Image 1", "", screen, user);
        image.setId(1L);
        Image image1 = new Image("Image 11", "1", screen, user);
        image1.setId(1L);

        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(imageRepository.save(image1)).thenReturn(image1);

        assertThat(imageService.update(image1)).isEqualTo(image1);
    }

    @Test
    @DisplayName("Update invalid image throws exception")
    void shouldThrowException_whenUpdateInvalidImage() {
        Image image = new Image("Image 1", "", screen, user);
        image.setId(1L);
        try {
            imageService.update(image);
            assertThat(true).isFalse();
        } catch (ImageNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find image 1");
        }
    }

    @Test
    @DisplayName("Delete image")
    void shouldDoNothing_whenDeleteImage() {
        imageService.delete(1L);
    }

    @TestConfiguration
    static class ImageServiceTestContextConfiguration {

        @Autowired
        private ImageRepository imageRepository;

        @Bean
        public ImageServiceInterface imageServiceInterface() {
            return new ImageService(imageRepository);
        }
    }
}
