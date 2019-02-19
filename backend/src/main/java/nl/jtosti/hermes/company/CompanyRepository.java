package nl.jtosti.hermes.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findAll();

    @Query("select c from Company c inner join c.users user ON user.id = :userId")
    List<Company> findCompaniesByUserId(@Param("userId") Long userId);

//    @Query("select c from Company c where c IN (SELECT location.company from Location location where location.company IN (select com from Company com inner join com.users user ON user.id = :userId))")
//    List<Company> findAdvertisingCompaniesByUserId(@Param("userId") Long userId);
}
