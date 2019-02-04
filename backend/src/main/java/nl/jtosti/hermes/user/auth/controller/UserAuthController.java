package nl.jtosti.hermes.user.auth.controller;

import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.auth.UserAuthServiceInterface;
import nl.jtosti.hermes.user.auth.dto.AuthUserDTO;
import nl.jtosti.hermes.user.auth.dto.NewUserDTO;
import nl.jtosti.hermes.user.dto.ExtendedUserDTO;
import nl.jtosti.hermes.user.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAuthController {

    private final UserAuthServiceInterface userAuthService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserAuthController(UserAuthServiceInterface userAuthService, ModelMapper modelMapper) {
        this.userAuthService = userAuthService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/auth/user/signin")
    public AuthUserDTO logIn(@RequestBody UserAuthenticationRequest request) {
        return convertToDTO(userAuthService.authenticate(request));
    }

    @GetMapping("/auth/user/refresh")
    public AuthUserDTO refreshToken(@AuthenticationPrincipal UserDetails userDetails) {
        return convertToDTO(userAuthService.refreshAuthentication(userDetails));
    }

    @GetMapping("/users/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return convertToExtendedDTO(userAuthService.getUserByEmail(userDetails.getUsername()));
    }

    @PostMapping("/auth/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody NewUserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User userCreated = userAuthService.save(user);
        return convertToExtendedDTO(userCreated);
    }

    private ExtendedUserDTO convertToExtendedDTO(User user) {
        return modelMapper.map(user, ExtendedUserDTO.class);
    }

    private AuthUserDTO convertToDTO(String token) {
        return new AuthUserDTO(token);
    }

    private User convertToEntity(NewUserDTO userDTO) {
        if (userDTO.getPassword() != null) {
            return new User(userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getEmail(),
                    userDTO.getPassword());
        }
        return null;
    }

}
