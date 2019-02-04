package nl.jtosti.hermes.image;

import nl.jtosti.hermes.image.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ImageService implements ImageServiceInterface {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> getImagesByScreenId(Long id) {
        return imageRepository.findAllByScreenId(id);
    }

//    @Override
//    public List<Image> getImagesByUserId(Long id) {
//        return imageRepository.findAllByOwnerId(id);
//    }

    @Override
    public List<Image> getImagesByLocationId(Long id) {
        return imageRepository.findAllByScreenLocationId(id);
    }

//    @Override
//    public List<Image> getImagesByLocationIdByUserId(Long locationId, Long userId) {
//        return imageRepository.findAllByScreenLocationIdAndOwnerId(locationId, userId);
//    }

    @Override
    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image update(Image newImage) {
        return imageRepository.findById(newImage.getId())
                .map(image -> {
                    image.setName(newImage.getName());
                    image.setUrl(newImage.getUrl());
                    return imageRepository.save(image);
                })
                .orElseThrow(
                        () -> new ImageNotFoundException(newImage.getId())
                );
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }
}
