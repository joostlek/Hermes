package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.Image;

import java.util.List;

public interface ImageDAO {
    Image get(int id);
    Image save(Image image);
    Image update(Image image);
    boolean delete(Image image);
    List<Image> getAll();
}
