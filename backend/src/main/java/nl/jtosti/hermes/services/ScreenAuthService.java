package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.requests.ScreenAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ScreenAuthService implements ScreenAuthServiceInterface {

    @Autowired
    private ScreenServiceInterface screenService;

    @Qualifier("screenAuthenticationProvider")
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String authenticate(ScreenAuthenticationRequest request) {
        try {
            String username = request.getUsername();
            Long screenId = Long.parseLong(username);
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
            Screen screen = screenService.getScreenById(screenId);
            return getToken(screen);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }

    }

    private String getToken(Screen screen) {
        return jwtTokenProvider.createToken(screen.getId().toString(), Collections.singletonList("SCREEN"));
    }
}
