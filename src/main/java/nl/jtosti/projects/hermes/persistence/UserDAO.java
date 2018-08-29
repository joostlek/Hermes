package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.User;

import java.util.List;

public interface UserDAO {
    User get(int id);
    User save(User user);
    User update(User user);
    boolean delete(User user);
    List<User> getAll();
    List<User> getAllByLocationId(int locationId);
    User getUserByLogin(String email, String password);
}
