package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.dto.LocationDTO;
import nl.jtosti.hermes.services.LocationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final ModelMapper modelMapper;
    private LocationServiceInterface locationService;

    @Autowired
    LocationController(LocationServiceInterface locationService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return locations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public LocationDTO addLocation(@RequestBody LocationDTO locationDTO) {
        Location location = convertToEntity(locationDTO);
        Location newLocation = locationService.save(location);
        return convertToDto(newLocation);
    }

    @GetMapping("{id}")
    public LocationDTO getSingleLocation(@PathVariable Long id) {
        return convertToDto(locationService.getLocationById(id));
    }

    @PutMapping("{id}")
    public LocationDTO updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable Long id) {
        Location location = convertToEntity(locationDTO);
        Location updatedLocation = locationService.update(location);
        return convertToDto(updatedLocation);
    }

    @DeleteMapping("{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
    }

    private LocationDTO convertToDto(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }

    private Location convertToEntity(LocationDTO locationDTO) {
        return modelMapper.map(locationDTO, Location.class);
    }
}
