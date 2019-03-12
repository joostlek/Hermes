package nl.jtosti.hermes.screen;

import nl.jtosti.hermes.location.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends CrudRepository<Screen, Long> {
    List<Screen> findAll();

    List<Screen> findAllByLocation(Location location);
}
