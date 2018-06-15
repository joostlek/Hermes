package nl.jtosti.projects.hermes;

import nl.jtosti.projects.hermes.models.Type;

import java.util.List;

public interface TypeDAO {
    Type get(int id);
    Type save(Type type);
    Type update(Type type);
    void delete(Type type);
    List<Type> getAll();
}
