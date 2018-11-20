package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Image;
import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.exceptions.ImageNotFoundException;
import nl.jtosti.hermes.repositories.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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
public class ImageServiceTest {

    @Autowired
    private ImageServiceInterface imageService;

    @MockBean
    private ImageRepository imageRepository;

    private User user;
    private Location location;
    private Screen screen;

    @Before
    public void setUp() {
        user = new User("Alex", "Jones", "alex.jones@alex.com");
        user.setId(1L);
        location = new Location("Alex Coffee", user);
        location.setId(1L);
        screen = new Screen("Screen 1", 1920, 1080, location);
        screen.setId(1L);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGivenValidId_thenReturnImage() {
        Image image = new Image("Image 1", "", screen, user);
        image.setId(1L);

        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        assertThat(imageService.getImageById(1L)).isEqualTo(image);
    }

    @Test
    public void whenGivenInvalidId_thenReturnNull() {
        assertThat(imageService.getImageById(2L)).isNull();
    }

    @Test
    public void returnAllImages() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "", screen, user);

        when(imageRepository.findAll()).thenReturn(Arrays.asList(image, image1));

        List<Image> images = imageService.getAllImages();

        assertThat(images).hasSize(2);
        assertThat(images.get(0)).isEqualTo(image);
        assertThat(images.get(1)).isEqualTo(image1);
    }

    @Test
    public void whenGivenScreenId_thenReturnImages() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "", screen, user);

        when(imageRepository.findAllByScreenId(1L)).thenReturn(Arrays.asList(image, image1));

        List<Image> images = imageService.getImagesByScreenId(1L);

        assertThat(images).hasSize(2);
        assertThat(images.get(0).getScreen().getId()).isEqualTo(1L);
        assertThat(images.get(1).getScreen().getId()).isEqualTo(1L);
    }

    @Test
    public void whenGivenOwnerId_thenReturnImages() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 2", "", screen, user);

        when(imageRepository.findAllByOwnerId(1L)).thenReturn(Arrays.asList(image, image1));

        List<Image> images = imageService.getImagesByUserId(1L);

        assertThat(images).hasSize(2);
        assertThat(images.get(0).getOwner().getId()).isEqualTo(1L);
        assertThat(images.get(1).getOwner().getId()).isEqualTo(1L);
    }

    @Test
    public void whenGivenImageId_returnBoolean() {
        when(imageRepository.existsById(1L)).thenReturn(true);
        when(imageRepository.existsById(2L)).thenReturn(false);

        assertThat(imageService.exists(1L)).isTrue();
        assertThat(imageService.exists(2L)).isFalse();
    }

    @Test
    public void testSaveImage_returnImage() {
        Image image = new Image("Image 1", "", screen, user);
        image.setId(1L);

        when(imageRepository.save(any(Image.class))).thenReturn(image);

        Image image1 = new Image("Image 1", "", screen, user);
        image1 = imageService.save(image1);
        assertThat(image1.getId()).isNotNull();
    }

    @Test
    public void whenUpdateImage_thenReturnUpdatedImage() {
        Image image = new Image("Image 1", "", screen, user);
        Image image1 = new Image("Image 11", "1", screen, user);

        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(imageRepository.save(image1)).thenReturn(image1);

        assertThat(imageService.update(image1, 1L)).isEqualTo(image1);
    }

    @Test
    public void whenUpdateImage_withFalseId_shouldThrowError() {
        Image image = new Image("Image 1", "", screen, user);
        try {
            imageService.update(image, 1L);
            assertThat(true).isFalse();
        } catch (ImageNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find image 1");
        }
    }

    @Test
    public void whenDeleteImage_thenDoNothing() {
        imageService.delete(1L);
    }

    @TestConfiguration
    static class ImageServiceTestContextConfiguration {
        @Bean
        public ImageServiceInterface imageServiceInterface() {
            return new ImageService();
        }
    }
}
