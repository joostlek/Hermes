package nl.jtosti.hermes.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findAll();

    @Query("select c from Company c left join c.users user ON user.id = :userId")
    List<Company> findCompaniesByUserId(@Param("userId") Long userId);
}
