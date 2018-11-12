package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Image;

import java.util.List;

public interface ImageService {
    public Image getImageById(Long id);

    public List<Image> getAllImages();

    public List<Image> getImagesByScreenId(Long id);

    public List<Image> getImagesByUserId(Long id);

    public boolean exists(Long id);

    public Image save(Image image);
}
