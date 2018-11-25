package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.entities.dto.ScreenDTO;
import nl.jtosti.hermes.services.LocationService;
import nl.jtosti.hermes.services.ScreenServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ScreenDTO> getAllScreens() {
        List<Screen> screens = screenService.getAllScreens();
        return screens.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/screens")
    public ScreenDTO addScreen(@RequestBody ScreenDTO screenDTO) {
        Screen screen = convertToEntity(screenDTO);
        Screen newScreen = screenService.save(screen);
        return convertToDto(newScreen);
    }

    @GetMapping("/screens/{id}")
    public ScreenDTO getOneScreen(@PathVariable("id") Long id) {
        return convertToDto(screenService.getScreenById(id));
    }

    @PutMapping("/screens/{id}")
    public ScreenDTO updateScreen(@RequestBody ScreenDTO screenDTO, @PathVariable("id") Long id) {
        Screen screen = convertToEntity(screenDTO);
        Screen updatedScreen = screenService.updateScreen(screen);
        return convertToDto(updatedScreen);
    }

    @DeleteMapping("/screens/{id}")
    public void deleteScreen(@PathVariable("id") Long id) {
        screenService.deleteScreen(id);
    }

    private ScreenDTO convertToDto(Screen screen) {
        return modelMapper.map(screen, ScreenDTO.class);
    }

    private Screen convertToEntity(ScreenDTO screenDTO) {
        Screen screen = modelMapper.map(screenDTO, Screen.class);
        screen.setLocation(locationService.getLocationById(screenDTO.getLocation().getId()));
        return screen;
    }
}
