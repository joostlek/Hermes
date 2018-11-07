package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
