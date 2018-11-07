package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLastName(String lastName);
}
