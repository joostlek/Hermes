package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.Screen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends CrudRepository<Screen, Long> {
}
