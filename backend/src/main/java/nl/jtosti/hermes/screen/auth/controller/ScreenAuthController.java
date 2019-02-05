package nl.jtosti.hermes.screen.auth.controller;

import nl.jtosti.hermes.screen.auth.ScreenAuthServiceInterface;
import nl.jtosti.hermes.screen.auth.dto.AuthScreenDTO;
import nl.jtosti.hermes.screen.auth.dto.PasswordDTO;
import nl.jtosti.hermes.security.requests.ScreenAuthenticationRequest;
import nl.jtosti.hermes.security.requests.ScreenRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreenAuthController {
    private final ScreenAuthServiceInterface screenAuthService;

    @Autowired
    public ScreenAuthController(ScreenAuthServiceInterface screenAuthService) {
        this.screenAuthService = screenAuthService;
    }

    @PostMapping("/auth/screen/signin")
    public AuthScreenDTO signin(@RequestBody ScreenAuthenticationRequest request) {
        return convertToDTO(screenAuthService.authenticate(request));
    }

    @PostMapping("/auth/screen/register")
    public PasswordDTO register(@RequestBody ScreenRegisterRequest request) {
        return convertToPasswordDTO(screenAuthService.register(request));
    }

    private AuthScreenDTO convertToDTO(String token) {
        return new AuthScreenDTO(token);
    }

    private PasswordDTO convertToPasswordDTO(String password) {
        return new PasswordDTO(password);
    }
}
