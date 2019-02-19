package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyService;
import nl.jtosti.hermes.company.exception.CompanyHasImagesException;
import nl.jtosti.hermes.company.exception.CompanyNotFoundException;
import nl.jtosti.hermes.location.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class LocationService implements LocationServiceInterface {

    private final LocationRepository locationRepository;
    private final CompanyService companyService;


    @Autowired
    public LocationService(LocationRepository locationRepository, CompanyService companyService) {
        this.locationRepository = locationRepository;
        this.companyService = companyService;
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
    public void addAdvertisingLocationToCompany(Long companyId, Long locationId) {
        Company company = companyService.getCompanyById(companyId);
        if (locationId == 0) {
            throw new LocationNotSelectedException();
        }
        Location location = this.getLocationById(locationId);
        if (location.getCompany().equals(company)) {
            throw new LocationIsFromCompanyException();
        }
        if (location.hasAdvertisingCompany(company)) {
            throw new LocationAlreadyAddedException();
        }
        location.addAdvertisingCompanies(company);
        locationRepository.save(location);
    }

    @Override
    public Location removeAdvertisingCompanyFromLocation(Long locationId, Long companyId) {
        if (locationId == 0) {
            throw new LocationNotFoundException(locationId);
        }
        if (companyId == 0) {
            throw new CompanyNotFoundException(companyId);
        }
        Location location = this.getLocationById(locationId);
        Company company = companyService.getCompanyById(companyId);
        if (!location.hasAdvertisingCompany(company)) {
            throw new CompanyNotAdvertisingException(company.getName(), location.getName());
        }
        if (company.getImagesByLocation(location).size() > 0) {
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
