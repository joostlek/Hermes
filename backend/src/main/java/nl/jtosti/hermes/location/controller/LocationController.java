package nl.jtosti.hermes.location.controller;

import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.location.dto.ExtendedLocationDTO;
import nl.jtosti.hermes.location.dto.LocationDTO;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import nl.jtosti.hermes.util.NotIdentifiedAsUserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationController {
    private final ModelMapper modelMapper;
    private final UserServiceInterface userService;
    private LocationServiceInterface locationService;

    @Autowired
    LocationController(LocationServiceInterface locationService, ModelMapper modelMapper, UserServiceInterface userService) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/locations")
    @Secured({"USER", "ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{userId}/locations")
    @Secured({"USER", "ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDTO addLocation(@RequestBody ExtendedLocationDTO locationDTO, @PathVariable Long userId, Principal principal) {
        Location location = convertToEntity(locationDTO);
        User owner = userService.getUserById(userId);
        if (!owner.getEmail().equals(principal.getName())) {
            throw new NotIdentifiedAsUserException(userId);
        }
//        location.a(owner);
        Location newLocation = locationService.save(location);
        return convertToExtendedDTO(newLocation);
    }

//    @GetMapping("/users/{userId}/locations")
//    @Secured({"USER", "ADMIN"})
//    @ResponseStatus(HttpStatus.OK)
//    public List<LocationDTO> getLocationByUserId(@PathVariable Long userId) {
//        List<Location> locations = locationService.getLocationsByUserId(userId);
//        return locations.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

//    @GetMapping("/users/{userId}/personal-locations")
//    @Secured({"USER", "ADMIN"})
//    @ResponseStatus(HttpStatus.OK)
//    public List<LocationDTO> getPersonalLocationByUserId(@PathVariable Long userId) {
//        List<Location> locations = locationService.getPersonalLocationsByUserId(userId);
//        return locations.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    @GetMapping("/locations/{id}")
    @Secured({"USER", "ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO getSingleLocation(@PathVariable Long id) {
        return convertToExtendedDTO(locationService.getLocationById(id));
    }

    @PutMapping("/locations/{id}")
    @Secured({"USER", "ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable Long id) {
        Location location = convertToEntity(locationDTO);
        location.setId(id);
        Location updatedLocation = locationService.update(location);
        return convertToExtendedDTO(updatedLocation);
    }

    @DeleteMapping("/locations/{id}")
    @Secured({"USER", "ADMIN"})
    @ResponseStatus(HttpStatus.OK)
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
