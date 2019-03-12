package nl.jtosti.hermes.screen.controller;

import nl.jtosti.hermes.config.V1ApiController;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.location.LocationServiceInterface;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
import nl.jtosti.hermes.screen.dto.ExtendedScreenDTO;
import nl.jtosti.hermes.screen.dto.PasswordDTO;
import nl.jtosti.hermes.screen.dto.ScreenDTO;
import nl.jtosti.hermes.screen.dto.ScreenRegisterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@V1ApiController
public class ScreenController {

    private final ModelMapper modelMapper;
    private final ScreenServiceInterface screenService;
    private final LocationServiceInterface locationService;


    @Autowired
    public ScreenController(ScreenServiceInterface screenService, ModelMapper modelMapper, LocationServiceInterface locationService) {
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
        Screen newScreen = screenService.addNewScreen(screen, location);
        return convertToExtendedDTO(newScreen);
    }

    @GetMapping("/locations/{locationId}/screens")
    @ResponseStatus(HttpStatus.OK)
    public List<ScreenDTO> getScreensByLocationId(@PathVariable Long locationId) {
        Location location = locationService.getLocationById(locationId);
        List<Screen> screens = screenService.getScreensByLocation(location);
        return screens.stream()
                .map(this::convertToDTO)
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

    @PostMapping("/screen/register")
    public PasswordDTO register(@RequestBody ScreenRegisterRequest request) {
        return convertToPasswordDTO(screenService.registerScreen(request.getScreenId()));
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

    private PasswordDTO convertToPasswordDTO(String password) {
        return new PasswordDTO(password);
    }
}
