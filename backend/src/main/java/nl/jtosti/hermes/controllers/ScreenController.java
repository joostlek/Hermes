package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.services.ScreenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    private final ScreenServiceInterface screenService;

    @Autowired
    public ScreenController(ScreenServiceInterface screenService) {
        this.screenService = screenService;
    }

    @GetMapping("")
    public List<Screen> getAllScreens() {
        return screenService.getAllScreens();
    }

    @PostMapping("")
    public Screen addScreen(@RequestBody Screen screen) {
        return screenService.save(screen);
    }

    @GetMapping("{id}")
    public Screen getOneScreen(@PathVariable("id") Long id) {
        return screenService.getScreenById(id);
    }

    @PutMapping("{id}")
    public Screen updateScreen(@RequestBody Screen screen, @PathVariable("id") Long id) {
        return screenService.updateScreen(screen, id);
    }
}
