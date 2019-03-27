package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.config.acl.AclServiceInterface;
import nl.jtosti.hermes.location.exception.LocationNotFoundException;
import nl.jtosti.hermes.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationService implements LocationServiceInterface {

    private final LocationRepository locationRepository;

    private final AclServiceInterface aclService;

    @Autowired
    public LocationService(LocationRepository locationRepository, AclServiceInterface aclService) {
        this.locationRepository = locationRepository;
        this.aclService = aclService;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("hasPermission(returnObject, 'EMPLOYEE')")
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
    }

    @Override
    @PostFilter("hasPermission(filterObject.company, 'EMPLOYEE')")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return locationRepository.existsById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#location.company, 'EMPLOYEE')")
    public Location save(Location location) {
        Location newLocation = saveLocation(location);
        aclService.addChildObjectWithParent(newLocation, newLocation.getCompany());
        return newLocation;
    }

    @Override
    @PostFilter("hasRole('ADMIN') or hasPermission(filterObject.company, 'EMPLOYEE')")
    public List<Location> getLocationsByCompanyId(Long companyId) {
        return locationRepository.findAllByCompanyId(companyId);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#newLocation.company, 'EMPLOYEE')")
    public Location update(Location newLocation) {
        return locationRepository.findById(newLocation.getId())
                .map(location -> {
                    location.setName(newLocation.getName());
                    location.setStreet(newLocation.getStreet());
                    location.setHouseNumber(newLocation.getHouseNumber());
                    location.setZipCode(newLocation.getZipCode());
                    location.setCountry(newLocation.getCountry());
                    location.setCity(newLocation.getCity());
                    return save(location);
                })
                .orElseThrow(
                        () -> new LocationNotFoundException(newLocation.getId())
                );
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#location.company, 'EMPLOYEE')")
    public void delete(Location location) {
        locationRepository.delete(location);
    }

    @Override
    @PreAuthorize("hasPermission(#company, 'EMPLOYEE')")
    public List<Location> getAdvertisingLocationsByCompany(Company company) {
        return locationRepository.findAllByAdvertisingCompany(company);
    }

    @Override
    @PreAuthorize("authentication.name == user.email")
    public List<Location> getAdvertisingLocationsByUser(User user) {
        return locationRepository.findAllAdvertisingCompaniesByUser(user);
    }

    @Override
    @PreAuthorize("authentication.name == user.email")
    public List<Location> getPersonalLocationsByUser(User user) {
        return locationRepository.findAllPersonalCompaniesByUser(user);
    }

    @Override
    @PreAuthorize("authentication.name == user.email")
    public List<Location> getAllLocationsByUser(User user) {
        return locationRepository.findAllCompaniesByUser(user);
    }

    private Location saveLocation(Location location) {
        return this.locationRepository.save(location);
    }
}
