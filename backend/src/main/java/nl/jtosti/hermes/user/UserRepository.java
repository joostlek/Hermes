package nl.jtosti.hermes.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByOrderByIdAsc();

    @Query("select u from User u left join company_users ON company_users.user_id = u.id WHERE company_users.company_id = ?1")
    List<User> findUsersByCompanies(Long id);
}
