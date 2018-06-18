package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Image;
import nl.jtosti.projects.hermes.models.User;

import java.util.List;

public interface ImageDAO {
    Image get(int id);
    Image save(Image image);
    Image update(Image image);
    boolean delete(Image image);
    List<Image> getAll();
    List<Image> getUncheckedImages(User user);
    Image updateActive(Image image);
}
