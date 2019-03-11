package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.user.User;

import java.util.List;

public interface LocationServiceInterface {
    Location getLocationById(Long id);

    List<Location> getAllLocations();

    boolean exists(Long id);

    Location save(Location location);

    List<Location> getLocationsByCompanyId(Long companyId);

    Location update(Location location);

    void delete(Location location);

    List<Location> getAdvertisingLocationsByCompany(Company company);

    List<Location> getAdvertisingLocationsByUser(User user);

    List<Location> getPersonalLocationsByUser(User user);

    List<Location> getAllLocationsByUser(User user);
}
