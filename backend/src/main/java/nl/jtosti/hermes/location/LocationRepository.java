package nl.jtosti.hermes.location;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();

    List<Location> findAllByCompanyId(Long id);

    @Query("select l from Location l inner join l.advertisingCompanies company ON company.id = :companyId")
    List<Location> findAllByAdvertisingCompanyId(Long companyId);

    @Query("select l from Location l inner join l.advertisingCompanies company inner join company.users user on user.id = :userId")
    List<Location> findAllAdvertisingCompaniesByUserId(Long userId);

    @Query("select l from Location l inner join l.company company inner join company.users user on user.id = :userId")
    List<Location> findAllPersonalCompaniesByUserId(Long userId);
}
