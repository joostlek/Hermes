package nl.jtosti.hermes.user.auth;

import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserAuthServiceInterface {
    private final AuthenticationProvider authenticationProvider;

    private final UserServiceInterface userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserAuthService(@Qualifier("userAuthenticationProvider") AuthenticationProvider authenticationProvider, UserServiceInterface userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationProvider = authenticationProvider;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String authenticate(UserAuthenticationRequest request) {
        try {
            String username = request.getUsername();
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
            User user = userService.getUserByEmail(username);
            return getToken(user);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }

    }

    @Override
    public String refreshAuthentication(UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.getUserByEmail(username);
        return getToken(user);
    }

    @Override
    public User save(User user) {
        return userService.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    private String getToken(User user) {
        return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
    }
}
