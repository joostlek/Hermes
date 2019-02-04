package nl.jtosti.hermes.user.controller;

import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import nl.jtosti.hermes.user.dto.ExtendedUserDTO;
import nl.jtosti.hermes.user.dto.UserDTO;
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
}
