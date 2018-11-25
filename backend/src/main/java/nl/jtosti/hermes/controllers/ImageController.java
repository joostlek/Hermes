package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Image;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.ImageDTO;
import nl.jtosti.hermes.services.ImageServiceInterface;
import nl.jtosti.hermes.services.ScreenService;
import nl.jtosti.hermes.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
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

    @GetMapping("")
    public List<ImageDTO> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return images.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ImageDTO getOneImage(@PathVariable Long id) {
        return convertToDto(imageService.getImageById(id));
    }

    @PutMapping("{id}")
    public ImageDTO updateImage(@RequestBody ImageDTO imageDTO, @PathVariable Long id) {
        Image image = convertToEntity(imageDTO);
        Image newImage = imageService.update(image);
        return convertToDto(newImage);
    }

    @PostMapping("")
    public Image saveImage(@RequestBody Image image) {
        return imageService.save(image);
    }

    @DeleteMapping("{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.delete(id);
    }

    private ImageDTO convertToDto(Image image) {
        return modelMapper.map(image, ImageDTO.class);
    }

    private Image convertToEntity(ImageDTO imageDTO) {
        Image image = modelMapper.map(imageDTO, Image.class);
        Screen screen = screenService.getScreenById(imageDTO.getScreen().getId());
        image.setScreen(screen);
        User user = userService.getUserById(imageDTO.getOwner().getId());
        image.setOwner(user);
        return image;
    }
}
