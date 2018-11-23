package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.User;

import java.util.List;

public interface UserServiceInterface {
    public User getUserById(Long id);

    public List<User> getAllUsers();

    public boolean exists(String email);

    public User save(User user);

    public User getUserByEmail(String email);

    User updateUser(User newUser);

    public void deleteUser(Long id);
}
