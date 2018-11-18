package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.User;
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
    public User addNewUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("{id}")
    public User getOneUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
