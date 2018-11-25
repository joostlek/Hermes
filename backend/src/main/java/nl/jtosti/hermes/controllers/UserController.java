package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.ExtendedUserDTO;
import nl.jtosti.hermes.entities.dto.UserDTO;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
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
        return convertToExtendedDTO(userService.updateUser(user));
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
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
