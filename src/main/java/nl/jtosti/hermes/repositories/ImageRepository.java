package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
