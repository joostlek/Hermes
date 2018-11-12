package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.Screen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends CrudRepository<Screen, Long> {
    List<Screen> findAll();

    List<Screen> findAllByLocationId(Long id);
}
