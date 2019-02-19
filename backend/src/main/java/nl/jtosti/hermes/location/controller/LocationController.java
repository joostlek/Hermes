package nl.jtosti.hermes.location.controller;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.company.CompanyServiceInterface;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.location.dto.ExtendedLocationDTO;
import nl.jtosti.hermes.location.dto.LocationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationController {
    private final ModelMapper modelMapper;
    private final LocationServiceInterface locationService;
    private final CompanyServiceInterface companyService;

    @Autowired
    LocationController(LocationServiceInterface locationService, ModelMapper modelMapper, CompanyServiceInterface companyService) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.companyService = companyService;
    }

    /**
     * @return DTO List of all locations
     */
    @GetMapping("/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param locationDTO Location data
     * @param companyId   Id of the to be parent company
     * @return Created location
     */
    @PostMapping("/companies/{companyId}/locations")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDTO addLocation(@RequestBody ExtendedLocationDTO locationDTO, @PathVariable Long companyId) {
        Location location = convertToEntity(locationDTO);
        Company company = companyService.getCompanyById(companyId);
        location.setCompany(company);
        Location newLocation = locationService.save(location);
        return convertToExtendedDTO(newLocation);
    }

    @GetMapping("/companies/{companyId}/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDTO> getLocationsByCompanyId(@PathVariable Long companyId) {
        List<Location> locations = locationService.getLocationsByCompanyId(companyId);
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
    public LocationDTO updateLocation(@RequestBody ExtendedLocationDTO locationDTO, @PathVariable Long id) {
        Location location = convertToEntity(locationDTO);
        location.setId(id);
        Location updatedLocation = locationService.update(location);
        return convertToExtendedDTO(updatedLocation);
    }

    @DeleteMapping("/locations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
    }

    @DeleteMapping("/locations/{locationId}/advertising/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public ExtendedLocationDTO removeAdvertisingCompanyFromLocation(@PathVariable Long locationId, @PathVariable Long companyId) {
        Location location = locationService.getLocationById(locationId);
        Company company = companyService.getCompanyById(companyId);
        return convertToExtendedDTO(locationService.removeAdvertisingCompanyFromLocation(location, company));
    }

    @GetMapping("/companies/{companyId}/advertising")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getAdvertisingLocations(@PathVariable Long companyId) {
        List<Location> locations = locationService.getAdvertisingLocationsByCompanyId(companyId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getAllLocationsByUserId(@PathVariable Long userId) {
        List<Location> locations = locationService.getAllLocationsByUserId(userId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/locations/advertising")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getAdvertisingLocationsByUserId(@PathVariable Long userId) {
        List<Location> locations = locationService.getAdvertisingLocationsByUserId(userId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/locations/personal")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getPersonalLocationsByUserId(@PathVariable Long userId) {
        List<Location> locations = locationService.getPersonalLocationsByUserId(userId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
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
