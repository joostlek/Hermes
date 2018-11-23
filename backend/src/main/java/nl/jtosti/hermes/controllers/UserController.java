package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.UserDTO;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServiceInterface userService;

    UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("")
    public User addNewUser(@RequestBody UserDTO user) {
        return userService.save(user.toUser());
    }

    @GetMapping("{id}")
    public User getOneUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@RequestBody UserDTO user, @PathVariable Long id) {
        user.setId(id);
        return userService.updateUser(user.toUser());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
