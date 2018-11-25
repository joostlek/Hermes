package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.ExtendedLocationDTO;
import nl.jtosti.hermes.entities.dto.LocationDTO;
import nl.jtosti.hermes.services.LocationServiceInterface;
import nl.jtosti.hermes.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{userId}/locations")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDTO addLocation(@RequestBody LocationDTO locationDTO, @PathVariable Long userId) {
        Location location = convertToEntity(locationDTO);
        User owner = userService.getUserById(userId);
        location.setOwner(owner);
        Location newLocation = locationService.save(location);
        return convertToExtendedDTO(newLocation);
    }

    @GetMapping("/users/{userId}/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDTO> getLocationByUserId(@PathVariable Long userId) {
        List<Location> locations = locationService.getLocationsByUserId(userId);
        return locations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/locations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO getSingleLocation(@PathVariable Long id) {
        return convertToExtendedDTO(locationService.getLocationById(id));
    }

    @PutMapping("/locations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable Long id) {
        Location location = convertToEntity(locationDTO);
        location.setId(id);
        Location updatedLocation = locationService.update(location);
        return convertToExtendedDTO(updatedLocation);
    }

    @DeleteMapping("/locations/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
    }

    private ExtendedLocationDTO convertToExtendedDTO(Location location) {
        return modelMapper.map(location, ExtendedLocationDTO.class);
    }

    private LocationDTO convertToDTO(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }

    private Location convertToEntity(LocationDTO locationDTO) {
        return modelMapper.map(locationDTO, Location.class);
    }
}
