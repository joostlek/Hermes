package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.services.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private LocationService locationService;

    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("")
    public Location addLocation(@RequestBody Location location) {
        return locationService.save(location);
    }

    @GetMapping("{id}")
    public Location getSingleLocation(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @PutMapping("{id}")
    public Location updateLocation(@RequestBody Location location, @PathVariable Long id) {
        return locationService.update(location, id);
    }

    @DeleteMapping("{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
    }
}
