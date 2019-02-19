package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.location.exception.CompanyHasImagesException;
import nl.jtosti.hermes.location.exception.CompanyNotAdvertisingException;
import nl.jtosti.hermes.location.exception.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationService implements LocationServiceInterface {

    private final LocationRepository locationRepository;


    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return locationRepository.existsById(id);
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getLocationsByCompanyId(Long companyId) {
        return locationRepository.findAllByCompanyId(companyId);
    }

    @Override
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
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location removeAdvertisingCompanyFromLocation(Location location, Company company) {
        if (!location.hasAdvertisingCompany(company)) {
            throw new CompanyNotAdvertisingException(company.getName(), location.getName());
        }
        if (!company.getImagesByLocation(location).isEmpty()) {
            throw new CompanyHasImagesException(company.getName());
        }
        location.getAdvertisingCompanies().remove(company);
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAdvertisingLocationsByCompanyId(Long companyId) {
        return locationRepository.findAllByAdvertisingCompanyId(companyId);
    }

    @Override
    public List<Location> getAdvertisingLocationsByUserId(Long userId) {
        return locationRepository.findAllAdvertisingCompaniesByUserId(userId);
    }

    @Override
    public List<Location> getPersonalLocationsByUserId(Long userId) {
        return locationRepository.findAllPersonalCompaniesByUserId(userId);
    }

    @Override
    public List<Location> getAllLocationsByUserId(Long userId) {
        return locationRepository.findAllCompaniesByUserId(userId);
    }
}
