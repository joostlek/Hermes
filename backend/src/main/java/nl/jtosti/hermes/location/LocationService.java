package nl.jtosti.hermes.location;

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
}
