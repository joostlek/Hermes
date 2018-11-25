package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.dto.ExtendedScreenDTO;
import nl.jtosti.hermes.entities.dto.ScreenDTO;
import nl.jtosti.hermes.services.LocationService;
import nl.jtosti.hermes.services.ScreenServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ScreenController {

    private final ModelMapper modelMapper;
    private final ScreenServiceInterface screenService;
    private final LocationService locationService;


    @Autowired
    public ScreenController(ScreenServiceInterface screenService, ModelMapper modelMapper, LocationService locationService) {
        this.screenService = screenService;
        this.modelMapper = modelMapper;
        this.locationService = locationService;
    }

    @GetMapping("/screens")
    @ResponseStatus(HttpStatus.OK)
    public List<ScreenDTO> getAllScreens() {
        List<Screen> screens = screenService.getAllScreens();
        return screens.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/locations/{locationId}/screens")
    @ResponseStatus(HttpStatus.CREATED)
    public ScreenDTO addScreen(@RequestBody ScreenDTO screenDTO, @PathVariable Long locationId) {
        Screen screen = convertToEntity(screenDTO);
        Location location = locationService.getLocationById(locationId);
        screen.setLocation(location);
        Screen newScreen = screenService.save(screen);
        return convertToExtendedDTO(newScreen);
    }

    @GetMapping("/locations/{locationId}/screens")
    @ResponseStatus(HttpStatus.OK)
    public List<ScreenDTO> getScreensByLocationId(@PathVariable Long locationId) {
        List<Screen> screens = screenService.getScreensByLocationId(locationId);
        return screens.stream()
                .map(this::convertToExtendedDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/screens/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScreenDTO getOneScreen(@PathVariable("id") Long id) {
        return convertToExtendedDTO(screenService.getScreenById(id));
    }

    @PutMapping("/screens/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScreenDTO updateScreen(@RequestBody ScreenDTO screenDTO, @PathVariable("id") Long id) {
        Screen screen = convertToEntity(screenDTO);
        screen.setId(id);
        Screen updatedScreen = screenService.updateScreen(screen);
        return convertToExtendedDTO(updatedScreen);
    }

    @DeleteMapping("/screens/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteScreen(@PathVariable("id") Long id) {
        screenService.deleteScreen(id);
    }

    private ExtendedScreenDTO convertToExtendedDTO(Screen screen) {
        return modelMapper.map(screen, ExtendedScreenDTO.class);
    }

    private ScreenDTO convertToDTO(Screen screen) {
        return modelMapper.map(screen, ScreenDTO.class);
    }

    private Screen convertToEntity(ScreenDTO screenDTO) {
        return modelMapper.map(screenDTO, Screen.class);
    }
}
