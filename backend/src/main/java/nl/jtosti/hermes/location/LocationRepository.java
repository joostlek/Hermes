package nl.jtosti.hermes.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();

//    List<Location> findAllByOwnerIdOrderByIdAsc(Long id);
//
//    @Query("SELECT DISTINCT loc FROM Location loc LEFT JOIN loc.screens scr LEFT JOIN scr.images ima LEFT JOIN ima.owner use WHERE use.id = :id OR loc.owner.id = :id ORDER BY loc.id")
//    List<Location> findPersonalLocationsByUserId(@Param("id") Long id);
}
