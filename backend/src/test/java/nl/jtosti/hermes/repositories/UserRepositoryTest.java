package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.services.StorageServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("User Repository")
@Tag("Repositories")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private StorageServiceInterface storageService;

    @Test
    public void findByLastName() {
        User user = new User("Alex", "Jones", "alex.jones@jones.com");
        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.findByEmail(user.getEmail());

        assertThat(found).isEqualTo(user);
    }


}