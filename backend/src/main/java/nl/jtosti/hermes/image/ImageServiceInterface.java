package nl.jtosti.hermes.image;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.location.Location;

import java.util.List;

public interface ImageServiceInterface {
    Image getImageById(Long id);

    List<Image> getAllImages();

    List<Image> getImagesByScreenId(Long id);

    List<Image> getImagesByCompany(Company company);

    List<Image> getImagesByLocation(Location location);

    List<Image> getImagesByLocationAndCompany(Location location, Company company);

    boolean exists(Long id);

    Image save(Image image);

    Image update(Image image);

    void delete(Image image);
}
