package nl.jtosti.hermes.security.providers;

import nl.jtosti.hermes.security.jwt.InvalidJwtAuthenticationException;
import nl.jtosti.hermes.services.ScreenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ScreenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private ScreenServiceInterface screenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (screenService.getScreenById(Long.parseLong(authentication.getName())) != null) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), Collections.emptyList());
        }
        System.out.println(authentication.getName());
        throw new InvalidJwtAuthenticationException("e");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
