package nl.jtosti.hermes.screen.auth;

import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.requests.ScreenAuthenticationRequest;
import nl.jtosti.hermes.security.requests.ScreenRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class ScreenAuthService implements ScreenAuthServiceInterface {

    private final ScreenServiceInterface screenService;

    private final AuthenticationProvider authenticationProvider;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public ScreenAuthService(ScreenServiceInterface screenService, @Qualifier("screenAuthenticationProvider") AuthenticationProvider authenticationProvider, JwtTokenProvider jwtTokenProvider) {
        this.screenService = screenService;
        this.authenticationProvider = authenticationProvider;
        this.jwtTokenProvider = jwtTokenProvider;
    }

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

    @Override
    public String register(ScreenRegisterRequest request) {
        Long screenId = request.getScreenId();
        return screenService.registerScreen(screenId);
    }

    private String getToken(Screen screen) {
        return jwtTokenProvider.createToken(screen.getId().toString(), Collections.singletonList("SCREEN"));
    }
}
