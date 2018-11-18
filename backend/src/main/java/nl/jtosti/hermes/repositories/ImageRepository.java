package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAll();

    List<Image> findAllByOwnerId(Long id);

    List<Image> findAllByScreenId(Long id);
}
