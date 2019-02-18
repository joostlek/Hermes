package nl.jtosti.hermes.image;

import java.util.List;

public interface ImageServiceInterface {
    Image getImageById(Long id);

    List<Image> getAllImages();

    List<Image> getImagesByScreenId(Long id);

    List<Image> getImagesByCompanyId(Long companyId);

    List<Image> getImagesByLocationId(Long id);

    List<Image> getImagesByLocationIdAndCompanyId(Long locationId, Long companyId);

    boolean exists(Long id);

    Image save(Image image);

    Image update(Image image);

    void delete(Long id);
}
