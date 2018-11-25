package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.UserDTO;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;

    private UserServiceInterface userService;

    @Autowired
    UserController(UserServiceInterface userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public UserDTO addNewUser(@RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User userCreated = userService.save(user);
        return convertToDto(userCreated);
    }

    @GetMapping("{id}")
    public UserDTO getOneUser(@PathVariable Long id) {
        return convertToDto(userService.getUserById(id));
    }

    @PutMapping("{id}")
    public User updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        User user = convertToEntity(userDTO);
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
