package nl.jtosti.hermes.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByOrderByIdAsc();

    @Query("select u from users u left join u.companies cu ON cu.id = :companyId")
    List<User> findUsersByCompanyId(@Param("companyId") Long id);
}
