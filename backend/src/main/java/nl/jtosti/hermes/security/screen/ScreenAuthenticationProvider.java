package nl.jtosti.hermes.security.screen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ScreenAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService screenLoginService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ScreenAuthenticationProvider(@Qualifier("screenLoginService") UserDetailsService screenLoginService, PasswordEncoder passwordEncoder) {
        this.screenLoginService = screenLoginService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        try {
            String screenId = authentication.getName();
            String password = authentication.getCredentials().toString();
            UserDetails userDetails = screenLoginService.loadUserByUsername(screenId);
            if (!userDetails.isCredentialsNonExpired()) {
                throw new ScreenPasswordExpiredException();
            }
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), userDetails.getAuthorities());
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
