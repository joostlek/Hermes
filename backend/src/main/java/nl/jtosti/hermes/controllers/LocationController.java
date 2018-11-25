package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.LocationDTO;
import nl.jtosti.hermes.services.LocationServiceInterface;
import nl.jtosti.hermes.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationController {
    private final ModelMapper modelMapper;
    private final UserService userService;
    private LocationServiceInterface locationService;

    @Autowired
    LocationController(LocationServiceInterface locationService, ModelMapper modelMapper, UserService userService) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/locations")
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return locations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/locations")
    public LocationDTO addLocation(@RequestBody LocationDTO locationDTO) {
        Location location = convertToEntity(locationDTO);
        Location newLocation = locationService.save(location);
        return convertToDto(newLocation);
    }

    @GetMapping("/locations/{id}")
    public LocationDTO getSingleLocation(@PathVariable Long id) {
        return convertToDto(locationService.getLocationById(id));
    }

    @PutMapping("/locations/{id}")
    public LocationDTO updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable Long id) {
        Location location = convertToEntity(locationDTO);
        Location updatedLocation = locationService.update(location);
        return convertToDto(updatedLocation);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
    }

    private LocationDTO convertToDto(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }

    private Location convertToEntity(LocationDTO locationDTO) {
        Location location = modelMapper.map(locationDTO, Location.class);
        User owner = userService.getUserById(locationDTO.getOwner().getId());
        location.setOwner(owner);
        return location;
    }
}
