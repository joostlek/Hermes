package nl.jtosti.hermes.image;

import java.util.List;

public interface ImageServiceInterface {
    Image getImageById(Long id);

    List<Image> getAllImages();

    List<Image> getImagesByScreenId(Long id);

    List<Image> getImagesByCompanyId(Long companyId);

//    public List<Image> getImagesByUserId(Long id);

    List<Image> getImagesByLocationId(Long id);

//    public List<Image> getImagesByLocationIdByUserId(Long locationId, Long userId);

    boolean exists(Long id);

    Image save(Image image);

    Image update(Image image);

    void delete(Long id);
}
