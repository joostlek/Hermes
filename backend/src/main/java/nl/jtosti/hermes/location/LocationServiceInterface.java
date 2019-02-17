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

    Location removeAdvertisingCompanyFromLocation(Long locationId, Long companyId);

    List<Location> getAdvertisingLocationsByCompanyId(Long companyId);

    List<Location> getAdvertisingLocationsByUserId(Long userId);

    List<Location> getPersonalLocationsByUserId(Long userId);

    List<Location> getAllLocationsByUserId(Long userId);
}
