package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.services.ScreenServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/screens")
public class ScreenController {
    private ScreenServiceInterface screenServiceInterface;
}
