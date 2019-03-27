package nl.jtosti.hermes.image;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.location.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAll();

    List<Image> findAllByCompany(Company company);

    List<Image> findAllByScreenId(Long id);

    List<Image> findAllByScreenLocation(Location location);

    List<Image> findAllByScreenLocationAndCompany(Location location, Company company);
}
