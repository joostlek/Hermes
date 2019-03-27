package nl.jtosti.hermes.image;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.image.exception.CacheFileNotFoundException;
import nl.jtosti.hermes.image.exception.ImageNotFoundException;
import nl.jtosti.hermes.location.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostAuthorize("hasRole('ADMIN') or hasPermission(returnObject, 'READ') or hasPermission(returnObject.company, 'EMPLOYEE') or hasPermission(returnObject.screen.location.company, 'EMPLOYEE')")
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('SCREEN') and authentication.name == id.toString()")
    public List<Image> getImagesByScreenId(Long id) {
        return imageRepository.findAllByScreenId(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(company, 'EMPLOYEE')")
    public List<Image> getImagesByCompany(Company company) {
        return imageRepository.findAllByCompany(company);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(location.company, 'EMPLOYEE')")
    public List<Image> getImagesByLocation(Location location) {
        return imageRepository.findAllByScreenLocation(location);
    }

    @Override
    @PreAuthorize("hasPermission(company, 'EMPLOYEE') or hasPermission(location.company, 'EMPLOYEE')")
    public List<Image> getImagesByLocationAndCompany(Location location, Company company) {
        return imageRepository.findAllByScreenLocationAndCompany(location, company);
    }

    @Override
    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission(image.company, 'EMPLOYEE')")
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
    @PreAuthorize("hasPermission(newImage.company, 'EMPLOYEE')")
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
    @PreAuthorize("hasRole('ADMIN') or hasPermission(image.company, 'EMPLOYEE') or hasPermission(image.screen.location.company, 'EMPLOYEE')")
    public void delete(Image image) {
        imageRepository.delete(image);
    }
}
