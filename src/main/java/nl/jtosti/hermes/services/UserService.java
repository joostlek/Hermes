package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.User;

import java.util.List;

public interface UserService {
    public User getUserById(Long id);

    public List<User> getAllUsers();

    public boolean exists(String username);

    public User save(User user);

    public User getUserByEmail(String email);
}
