package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();

    List<Location> findAllByCompanyId(Long id);

    @Query("select l from Location l inner join l.advertisingCompanies company ON company = :company")
    List<Location> findAllByAdvertisingCompany(Company company);

    @Query("select l from Location l inner join l.advertisingCompanies company inner join company.users user on user = :user")
    List<Location> findAllAdvertisingCompaniesByUser(User user);

    @Query("select l from Location l inner join l.company company inner join company.users user on user = :user")
    List<Location> findAllPersonalCompaniesByUser(User user);

    @Query("SELECT l from Location l where l.id IN (SELECT loc.id from Location loc inner join loc.company company inner join company.users user on user = :user) " +
            "or l.id IN (select loc2.id from Location loc2 inner join loc2.advertisingCompanies company inner join company.users user on user = :user)")
    List<Location> findAllCompaniesByUser(User user);
}
