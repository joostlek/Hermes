package nl.jtosti.hermes.image;

import nl.jtosti.hermes.image.exception.CacheFileNotFoundException;
import nl.jtosti.hermes.image.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ImageService implements ImageServiceInterface {

    private final ImageRepository imageRepository;

    private final StorageServiceInterface storageService;

    @Autowired
    public ImageService(ImageRepository imageRepository, StorageServiceInterface storageService) {
        this.imageRepository = imageRepository;
        this.storageService = storageService;
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

    @Override
    public List<Image> getImagesByCompanyId(Long companyId) {
        return imageRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Image> getImagesByLocationId(Long id) {
        return imageRepository.findAllByScreenLocationId(id);
    }

    @Override
    public List<Image> getImagesByLocationIdAndCompanyId(Long locationId, Long companyId) {
        return imageRepository.findAllByScreenLocationIdAndCompanyId(locationId, companyId);
    }

    @Override
    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }

    @Override
    @Transactional
    public Image save(Image image) {
        if (storageService.cacheFileExist(image.getUrl())) {
            Image image1 = imageRepository.save(image);
            String url = storageService.moveToPersistentLocation(image1.getUrl(), image1.getId());
            image1.setUrl(url);
            return imageRepository.save(image1);
        } else {
            throw new CacheFileNotFoundException();
        }
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
