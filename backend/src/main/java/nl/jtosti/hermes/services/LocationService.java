package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Location;

import java.util.List;

public interface LocationService {
    public Location getLocationById(Long id);

    public List<Location> getAllLocations();

    public boolean exists(Long id);

    public Location save(Location location);

    public List<Location> getLocationsByUserId(Long id);

    public Location update(Location location, Long id);

    public void delete(Long id);
}