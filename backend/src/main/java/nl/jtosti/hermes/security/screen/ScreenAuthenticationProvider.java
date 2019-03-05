package nl.jtosti.hermes.security.screen;

import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.screen.ScreenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ScreenAuthenticationProvider implements AuthenticationProvider {
    private final ScreenServiceInterface screenService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ScreenAuthenticationProvider(ScreenServiceInterface screenService, PasswordEncoder passwordEncoder) {
        this.screenService = screenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        try {
            String screenId = authentication.getName();
            String password = authentication.getCredentials().toString();
            Screen screen = screenService.getScreenById(Long.parseLong(screenId));
            if (screen.isToReceivePassword()) {
                throw new ScreenPasswordExpiredException();
            }
            if (passwordEncoder.matches(password, screen.getPassword())) {
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), screen.getAuthorities());
            } else {
                throw new BadCredentialsException("");
            }
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
