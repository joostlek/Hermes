package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Image;

import java.util.List;

public interface ImageServiceInterface {
    public Image getImageById(Long id);

    public List<Image> getAllImages();

    public List<Image> getImagesByScreenId(Long id);

    public List<Image> getImagesByUserId(Long id);

    public List<Image> getImagesByLocationId(Long id);

    public List<Image> getImagesByLocationIdByUserId(Long locationId, Long userId);

    public boolean exists(Long id);

    public Image save(Image image);

    public Image update(Image image);

    public void delete(Long id);
}
