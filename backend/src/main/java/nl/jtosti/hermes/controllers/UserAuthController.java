package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.dto.AuthUserDTO;
import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
import nl.jtosti.hermes.services.UserAuthServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {
    @Autowired
    private UserAuthServiceInterface userAuthService;

    @PostMapping("/auth/user/signin")
    public AuthUserDTO signin(@RequestBody UserAuthenticationRequest request) {
        return convertToDTO(userAuthService.authenticate(request));
    }

    @GetMapping("/auth/user/refresh")
    public AuthUserDTO refresh(@AuthenticationPrincipal UserDetails userDetails) {
        return convertToDTO(userAuthService.refreshAuthentication(userDetails));
    }

    private AuthUserDTO convertToDTO(String token) {
        return new AuthUserDTO(token);
    }
}
