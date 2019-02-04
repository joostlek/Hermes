package nl.jtosti.hermes.image.controller;

import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.image.ImageServiceInterface;
import nl.jtosti.hermes.image.StorageService;
import nl.jtosti.hermes.image.dto.ExtendedImageDTO;
import nl.jtosti.hermes.image.dto.FileDTO;
import nl.jtosti.hermes.image.dto.ImageDTO;
import nl.jtosti.hermes.image.exception.FileStoreException;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ImageController {
    private final ImageServiceInterface imageService;

    private final ModelMapper modelMapper;

    private final ScreenServiceInterface screenService;

    private final UserServiceInterface userService;

    private final StorageService storageService;


    @Autowired
    public ImageController(ImageServiceInterface imageService, ModelMapper modelMapper, ScreenServiceInterface screenService, UserServiceInterface userService, StorageService storageService) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.screenService = screenService;
        this.userService = userService;
        this.storageService = storageService;
    }

    @GetMapping("/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return images.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    @GetMapping("/users/{userId}/images")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ImageDTO> getImagesByUserId(@PathVariable Long userId) {
//        List<Image> images = imageService.getImagesByUserId(userId);
//        return images.stream()
//                .map(this::convertToExtendedDTO)
//                .collect(Collectors.toList());
//    }

    @GetMapping("/screens/{screenId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getImagesByScreenId(@PathVariable Long screenId) {
        List<Image> images = imageService.getImagesByScreenId(screenId);
        return images.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/locations/{locationId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getImagesByLocationId(@PathVariable Long locationId) {
        List<Image> images = imageService.getImagesByLocationId(locationId);
        return images.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

//    @GetMapping("/users/{userId}/locations/{locationId}/images")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ImageDTO> getImagesByLocationIdByUserId(@PathVariable Long userId, @PathVariable Long locationId) {
//        List<Image> images = imageService.getImagesByLocationIdByUserId(locationId, userId);
//        return images.stream()
//                .map(this::convertToExtendedDTO)
//                .collect(Collectors.toList());
//    }

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
        image.setUploader(owner);
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

    @PostMapping("/images/upload")
    @ResponseStatus(HttpStatus.OK)
    public FileDTO uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String path = storageService.store(file);
            return new FileDTO(path);
        } catch (Exception e) {
            throw new FileStoreException("uploadFile");
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
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
