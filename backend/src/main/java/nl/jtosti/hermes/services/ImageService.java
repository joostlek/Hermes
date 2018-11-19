package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Image;
import nl.jtosti.hermes.exceptions.ImageNotFoundException;
import nl.jtosti.hermes.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ImageService implements ImageServiceInterface {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> getImagesByScreenId(Long id) {
        return imageRepository.findAllByScreenId(id);
    }

    @Override
    public List<Image> getImagesByUserId(Long id) {
        return imageRepository.findAllByOwnerId(id);
    }

    @Override
    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image update(Image newImage, Long id) {
        return imageRepository.findById(id)
                .map(image -> {
                    image.setName(newImage.getName());
                    image.setUrl(newImage.getUrl());
                    return imageRepository.save(image);
                })
                .orElseThrow(
                        () -> new ImageNotFoundException(id)
                );
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }
}
