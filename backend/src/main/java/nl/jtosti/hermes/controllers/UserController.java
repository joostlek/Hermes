package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.ExtendedUserDTO;
import nl.jtosti.hermes.entities.dto.NewUserDTO;
import nl.jtosti.hermes.entities.dto.UserDTO;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final ModelMapper modelMapper;

    private UserServiceInterface userService;

    @Autowired
    UserController(UserServiceInterface userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody NewUserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User userCreated = userService.save(user);
        return convertToExtendedDTO(userCreated);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getOneUser(@PathVariable Long id) {
        return convertToExtendedDTO(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        User user = convertToEntity(userDTO);
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return convertToExtendedDTO(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return convertToExtendedDTO(userService.getUserByEmail(userDetails.getUsername()));
    }

    private ExtendedUserDTO convertToExtendedDTO(User user) {
        return modelMapper.map(user, ExtendedUserDTO.class);
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
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
