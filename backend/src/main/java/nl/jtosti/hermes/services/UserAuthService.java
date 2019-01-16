package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
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
    @Qualifier("userAuthenticationProvider")
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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

    private String getToken(User user) {
        return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
    }
}
