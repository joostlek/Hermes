package nl.jtosti.hermes.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Database solution for the {@link User} entity.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * @param email Email of the user
     * @return optional user
     */
    Optional<User> findByEmail(String email);

    /**
     * @return List of all users ordered by {@link User#getId()}
     */
    List<User> findAllByOrderByIdAsc();

    @Query("select u from users u inner join u.companies cu ON cu.id = :companyId")
    List<User> findUsersByCompanyId(@Param("companyId") Long id);
}
