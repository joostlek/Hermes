package nl.jtosti.hermes.user;

import nl.jtosti.hermes.company.Company;

import java.util.List;

public interface UserServiceInterface {
    User getUserById(Long id);

    List<User> getAllUsers();

    boolean exists(String email);

    User save(User user);

    User getUserByEmail(String email);

    User updateUser(User newUser);

    void deleteUser(User user);

    List<User> getAllUsersByCompany(Company company);
}
