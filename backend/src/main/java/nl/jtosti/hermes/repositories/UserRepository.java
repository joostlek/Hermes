package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAllByOrderByIdAsc();
}
