package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Location;

import java.util.List;

public interface LocationDAO {
    Location get(int id);
    Location save(Location location);
    Location update(Location location);
    boolean delete(Location location);
    List<Location> getAll();
}
