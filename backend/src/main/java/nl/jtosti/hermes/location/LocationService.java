package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyService;
import nl.jtosti.hermes.location.exception.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        Location location = this.getLocationById(locationId);
        location.addAdvertisingCompanies(company);
        this.save(location);
    }

    @Override
    public List<Location> getAdvertisingLocationsByCompanyId(Long companyId) {
        return locationRepository.findAllByAdvertisingCompanyId(companyId);
    }
}
