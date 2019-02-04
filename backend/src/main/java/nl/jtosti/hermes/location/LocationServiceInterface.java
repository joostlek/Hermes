package nl.jtosti.hermes.location;

import java.util.List;

public interface LocationServiceInterface {
    Location getLocationById(Long id);

    List<Location> getAllLocations();

    boolean exists(Long id);

    Location save(Location location);

//    public List<Location> getLocationsByUserId(Long id);

    Location update(Location location);

    void delete(Long id);

//    public List<Location> getPersonalLocationsByUserId(Long id);
}
