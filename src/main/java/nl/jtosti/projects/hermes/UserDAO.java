package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.User;

public interface UserDAO {
    public User save(User user);
    public User get(int id);
    public User update(User user);
    public void delete(User user);
}
