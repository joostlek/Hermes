package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.UserType;
import nl.jtosti.hermes.entities.dto.AuthScreenDTO;
import nl.jtosti.hermes.entities.dto.AuthUserDTO;
import nl.jtosti.hermes.entities.dto.PasswordDTO;
import nl.jtosti.hermes.security.jwt.JwtTokenFactory;
import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.providers.AuthenticationProviderFactory;
import nl.jtosti.hermes.security.requests.ScreenAuthenticationRequest;
import nl.jtosti.hermes.security.requests.ScreenRegisterRequest;
import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
import nl.jtosti.hermes.services.ScreenServiceInterface;
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
    private final ScreenServiceInterface screenService;

    @Autowired
    public AuthController(JwtTokenProvider jwtTokenProvider, UserServiceInterface userService, AuthenticationProviderFactory authenticationProviderFactory, ScreenServiceInterface screenService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.authenticationProviderFactory = authenticationProviderFactory;
        this.screenService = screenService;
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody UserAuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationProviderFactory.setUserType(UserType.USER);
            AuthenticationProvider authenticationProvider = authenticationProviderFactory.getObject();
            assert authenticationProvider != null;
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            User user = userService.getUserByEmail(username);
            JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(username, user.getRoles(), jwtTokenProvider);
            AuthUserDTO userDTO = new AuthUserDTO(user, jwtTokenFactory.getToken());
            return ok(userDTO);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        } catch (Exception e) {
            throw new BadCredentialsException("");
        }
    }

    @PostMapping("/screens/signin")
    public ResponseEntity screenSignIn(@RequestBody ScreenAuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationProviderFactory.setUserType(UserType.SCREEN);
            AuthenticationProvider authenticationProvider = authenticationProviderFactory.getObject();
            assert authenticationProvider != null;
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(username, Collections.singletonList("SCREEN"), jwtTokenProvider);
            AuthScreenDTO screenDTO = new AuthScreenDTO(screenService.getScreenById(Long.parseLong(username)), jwtTokenFactory.getToken());
            return ok(screenDTO);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        } catch (Exception e) {
            throw new RuntimeException(e.getClass().toString());
        }
    }

    @PostMapping("/screens/register")
    public ResponseEntity screenRegister(@RequestBody ScreenRegisterRequest registerRequest) {
        Long screenId = registerRequest.getScreenId();
        String password = screenService.registerScreen(screenId);
        PasswordDTO passwordDTO = new PasswordDTO(password);
        return ok(passwordDTO);
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