package nl.jtosti.hermes.image;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAll();

    List<Image> findAllByCompanyId(Long companyId);

    List<Image> findAllByScreenId(Long id);

    List<Image> findAllByScreenLocationId(Long id);
}
