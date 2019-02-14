package nl.jtosti.hermes.location;

import java.util.List;

public interface LocationServiceInterface {
    Location getLocationById(Long id);

    List<Location> getAllLocations();

    boolean exists(Long id);

    Location save(Location location);

    List<Location> getLocationsByCompanyId(Long companyId);

    Location update(Location location);

    void delete(Long id);

    void addAdvertisingLocationToCompany(Long companyId, Long locationId);

    List<Location> getAdvertisingLocationsByCompanyId(Long companyId);
}
