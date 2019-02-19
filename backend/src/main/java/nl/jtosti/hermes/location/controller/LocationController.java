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

    /**
     * @param companyId Company id to return the locations of
     * @return List of locations from the company
     */
    @GetMapping("/companies/{companyId}/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDTO> getLocationsByCompanyId(@PathVariable Long companyId) {
        List<Location> locations = locationService.getLocationsByCompanyId(companyId);
        return locations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param locationId Location id to return
     * @return location
     */
    @GetMapping("/locations/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO getSingleLocation(@PathVariable Long locationId) {
        return convertToExtendedDTO(locationService.getLocationById(locationId));
    }

    /**
     * @param locationDTO updated location data
     * @param locationId  location id to be updated
     * @return updated location
     */
    @PutMapping("/locations/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO updateLocation(@RequestBody ExtendedLocationDTO locationDTO, @PathVariable Long locationId) {
        Location location = convertToEntity(locationDTO);
        location.setId(locationId);
        Location updatedLocation = locationService.update(location);
        return convertToExtendedDTO(updatedLocation);
    }

    /**
     * @param locationId location id to be deleted
     */
    @DeleteMapping("/locations/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocation(@PathVariable Long locationId) {
        locationService.delete(locationId);
    }

    /**
     * @param locationId Location with company to be removed
     * @param companyId  Company to be removed from location
     * @return updated location
     */
    @DeleteMapping("/locations/{locationId}/advertising/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public ExtendedLocationDTO removeAdvertisingCompanyFromLocation(@PathVariable Long locationId, @PathVariable Long companyId) {
        Location location = locationService.getLocationById(locationId);
        Company company = companyService.getCompanyById(companyId);
        return convertToExtendedDTO(locationService.removeAdvertisingCompanyFromLocation(location, company));
    }

    /**
     * @param companyId company id
     * @return list of advertising companies from the company
     */
    @GetMapping("/companies/{companyId}/advertising")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getAdvertisingLocationsByCompanyId(@PathVariable Long companyId) {
        List<Location> locations = locationService.getAdvertisingLocationsByCompanyId(companyId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param userId user to get locations
     * @return list of all personal and advertising location from user
     */
    @GetMapping("/users/{userId}/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getAllLocationsByUserId(@PathVariable Long userId) {
        List<Location> locations = locationService.getAllLocationsByUserId(userId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param userId user to get advertising locations
     * @return list of all advertising locations from user
     */
    @GetMapping("/users/{userId}/locations/advertising")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getAdvertisingLocationsByUserId(@PathVariable Long userId) {
        List<Location> locations = locationService.getAdvertisingLocationsByUserId(userId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param userId user to get personal locations
     * @return list of all personal locations from user
     */
    @GetMapping("/users/{userId}/locations/personal")
    @ResponseStatus(HttpStatus.OK)
    public List<ExtendedLocationDTO> getPersonalLocationsByUserId(@PathVariable Long userId) {
        List<Location> locations = locationService.getPersonalLocationsByUserId(userId);
        return locations.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param location location to be turned into {@link ExtendedLocationDTO}
     * @return LocationDTO
     */
    private ExtendedLocationDTO convertToExtendedDTO(Location location) {
        return modelMapper.map(location, ExtendedLocationDTO.class);
    }

    /**
     * @param location location to be turned into {@link LocationDTO}
     * @return LocationDTO
     */
    private LocationDTO convertToDTO(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }

    /**
     * @param locationDTO DTO to be turned into {@link Location}
     * @return location
     */
    private Location convertToEntity(LocationDTO locationDTO) {
        return modelMapper.map(locationDTO, Location.class);
    }
}
