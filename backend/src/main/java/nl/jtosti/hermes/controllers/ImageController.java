package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Image;
import nl.jtosti.hermes.services.ImageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageServiceInterface imageService;

    @Autowired
    public ImageController(ImageServiceInterface imageService) {
        this.imageService = imageService;
    }

    @GetMapping("")
    public List<Image> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("{id}")
    public Image getOneImage(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @PutMapping("{id}")
    public Image updateImage(@RequestBody Image image, @PathVariable Long id) {
        return imageService.update(image, id);
    }

    @PostMapping("")
    public Image saveImage(@RequestBody Image image) {
        return imageService.save(image);
    }

    @DeleteMapping("{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.delete(id);
    }
}
