package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Image;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.ExtendedImageDTO;
import nl.jtosti.hermes.entities.dto.ImageDTO;
import nl.jtosti.hermes.services.ImageServiceInterface;
import nl.jtosti.hermes.services.ScreenService;
import nl.jtosti.hermes.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ImageController {
    private final ImageServiceInterface imageService;

    private final ModelMapper modelMapper;

    private final ScreenService screenService;

    private final UserService userService;

    @Autowired
    public ImageController(ImageServiceInterface imageService, ModelMapper modelMapper, ScreenService screenService, UserService userService) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.screenService = screenService;
        this.userService = userService;
    }

    @GetMapping("/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return images.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getImagesByUserId(@PathVariable Long userId) {
        List<Image> images = imageService.getImagesByUserId(userId);
        return images.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/screens/{screenId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getImagesByScreenId(@PathVariable Long screenId) {
        List<Image> images = imageService.getImagesByScreenId(screenId);
        return images.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/images/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDTO getOneImage(@PathVariable Long id) {
        return convertToExtendedDTO(imageService.getImageById(id));
    }

    @PutMapping("/images/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDTO updateImage(@RequestBody ImageDTO imageDTO, @PathVariable Long id) {
        Image image = convertToEntity(imageDTO);
        image.setId(id);
        Image newImage = imageService.update(image);
        return convertToExtendedDTO(newImage);
    }

    @PostMapping(value = "/users/{userId}/images", params = {"screenId"})
    @ResponseStatus(HttpStatus.CREATED)
    public ImageDTO saveImage(@RequestBody ImageDTO imageDTO, @PathVariable Long userId, @RequestParam Long screenId) {
        Image image = convertToEntity(imageDTO);
        User owner = userService.getUserById(userId);
        image.setOwner(owner);
        Screen screen = screenService.getScreenById(screenId);
        image.setScreen(screen);
        Image newImage = imageService.save(image);
        return convertToExtendedDTO(newImage);
    }

    @DeleteMapping("/images/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable Long id) {
        imageService.delete(id);
    }

    private ExtendedImageDTO convertToExtendedDTO(Image image) {
        return modelMapper.map(image, ExtendedImageDTO.class);
    }

    private ImageDTO convertToDTO(Image image) {
        return modelMapper.map(image, ImageDTO.class);
    }

    private Image convertToEntity(ImageDTO imageDTO) {
        return modelMapper.map(imageDTO, Image.class);
    }
}
