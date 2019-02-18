package nl.jtosti.hermes.image.controller;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.image.ImageServiceInterface;
import nl.jtosti.hermes.image.StorageServiceInterface;
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

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ImageController {
    private final ImageServiceInterface imageService;

    private final ModelMapper modelMapper;

    private final ScreenServiceInterface screenService;

    private final UserServiceInterface userService;

    private final StorageServiceInterface storageService;

    private final CompanyServiceInterface companyService;

    @Autowired
    public ImageController(ImageServiceInterface imageService, ModelMapper modelMapper, ScreenServiceInterface screenService, UserServiceInterface userService, StorageServiceInterface storageService, CompanyServiceInterface companyService) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.screenService = screenService;
        this.userService = userService;
        this.storageService = storageService;
        this.companyService = companyService;
    }


    @GetMapping("/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return images.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/companies/{companyId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getImagesByCompanyId(@PathVariable Long companyId) {
        List<Image> images = imageService.getImagesByCompanyId(companyId);
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

    @GetMapping("/locations/{locationId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getImagesByLocationId(@PathVariable Long locationId) {
        List<Image> images = imageService.getImagesByLocationId(locationId);
        return images.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/locations/{locationId}/images", params = {"companyId"})
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getImagesByLocationIdByCompanyId(@PathVariable Long locationId,
                                                           @RequestParam Long companyId) {
        List<Image> images = imageService.getImagesByLocationIdAndCompanyId(locationId, companyId);
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

    @PostMapping(value = "/companies/{companyId}/images", params = {"screenId"})
    @ResponseStatus(HttpStatus.CREATED)
    public ImageDTO saveImage(@RequestBody ImageDTO imageDTO,
                              @PathVariable Long companyId,
                              @RequestParam Long screenId,
                              Principal principal) {
        Image image = convertToEntity(imageDTO);
        User uploader = userService.getUserByEmail(principal.getName());
        image.setUploader(uploader);
        Screen screen = screenService.getScreenById(screenId);
        image.setScreen(screen);
        Company company = companyService.getCompanyById(companyId);
        image.setCompany(company);
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
