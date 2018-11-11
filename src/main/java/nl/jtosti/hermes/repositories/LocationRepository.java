package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();

    List<Location> findAllByOwnerIdOrderByIdAsc(Long id);
}
