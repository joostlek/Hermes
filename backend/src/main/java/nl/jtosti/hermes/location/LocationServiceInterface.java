package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;

import java.util.List;

public interface LocationServiceInterface {
    Location getLocationById(Long id);

    List<Location> getAllLocations();

    boolean exists(Long id);

    Location save(Location location);

    List<Location> getLocationsByCompanyId(Long companyId);

    Location update(Location location);

    void delete(Long id);

    Location removeAdvertisingCompanyFromLocation(Location location, Company company);

    List<Location> getAdvertisingLocationsByCompanyId(Long companyId);

    List<Location> getAdvertisingLocationsByUserId(Long userId);

    List<Location> getPersonalLocationsByUserId(Long userId);

    List<Location> getAllLocationsByUserId(Long userId);
}
