package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.UserType;
import nl.jtosti.hermes.security.AuthenticationProviderFactory;
import nl.jtosti.hermes.security.AuthenticationRequest;
import nl.jtosti.hermes.security.JwtTokenFactory;
import nl.jtosti.hermes.security.JwtTokenProvider;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceInterface userService;

    private final AuthenticationProviderFactory authenticationProviderFactory;

    @Autowired
    public AuthController(JwtTokenProvider jwtTokenProvider, UserServiceInterface userService, AuthenticationProviderFactory authenticationProviderFactory) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.authenticationProviderFactory = authenticationProviderFactory;
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationProviderFactory.setUserType(UserType.USER);
            AuthenticationProvider authenticationProvider = authenticationProviderFactory.getObject();
            assert authenticationProvider != null;
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(userService.getUserByEmail(username).getRoles(), username, jwtTokenProvider);
            return ok(jwtTokenFactory.getResponse());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        } catch (Exception e) {
            throw new BadCredentialsException("");
        }
    }

    @PostMapping("/screens/signin")
    public ResponseEntity screenSignIn(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationProviderFactory.setUserType(UserType.SCREEN);
            AuthenticationProvider authenticationProvider = authenticationProviderFactory.getObject();
            assert authenticationProvider != null;
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(Collections.singletonList("SCREEN"), username, jwtTokenProvider);
            return ok(jwtTokenFactory.getResponse());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList())
        );
        return ok(model);
    }
}