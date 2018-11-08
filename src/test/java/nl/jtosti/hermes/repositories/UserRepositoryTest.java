package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByLastName() {
        User user = new User("Alex", "Jones");
        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.findByLastName(user.getLastName()).get(0);

        assertThat(found).isEqualTo(user);
    }


}