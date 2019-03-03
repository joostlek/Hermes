package nl.jtosti.hermes.security.user;

import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceInterface userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthenticationProvider(PasswordEncoder passwordEncoder, UserServiceInterface userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        try {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            User user = userService.getUserByEmail(username);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), user.getAuthorities());
            } else {
                throw new BadCredentialsException("kek");
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
