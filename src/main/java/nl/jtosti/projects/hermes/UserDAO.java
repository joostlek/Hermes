package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.User;

import java.util.List;

public interface UserDAO {
    User get(int id);
    User save(User user);
    User update(User user);
    void delete(User user);
    List<User> getAll();
}
