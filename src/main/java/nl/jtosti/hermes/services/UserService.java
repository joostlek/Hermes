package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.User;

import java.util.List;

public interface UserService {
    public User getUserById(Long id);

    public List<User> getAllUsers();

    public boolean exists(String email);

    public User save(User user);

    public User getUserByEmail(String email);

    public User updateUser(User user, Long id);

    public void deleteUser(Long id);
}
