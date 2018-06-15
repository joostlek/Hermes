package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.User;

import java.util.List;

public interface UserDAO {
    User save(User user);
    User get(int id);
    User update(User user);
    void delete(User user);
    List getAll();
}
