package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.exceptions.LocationNotFoundException;
import nl.jtosti.hermes.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

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
    public List<Location> getLocationsByUserId(Long id) {
        return locationRepository.findAllByOwnerIdOrderByIdAsc(id);
    }

    @Override
    public Location update(Location newLocation, Long id) {
        return locationRepository.findById(id)
                .map(location -> {
                    location.setName(newLocation.getName());
                    return save(location);
                })
                .orElseThrow(
                        () -> new LocationNotFoundException(id)
                );
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}