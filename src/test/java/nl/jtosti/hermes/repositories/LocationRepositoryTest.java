package nl.jtosti.hermes.repositories;

import nl.jtosti.hermes.entities.Location;
import nl.jtosti.hermes.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void whenGivenUserId_thenReturnLocations() {
        User user = entityManager.persistAndFlush(new User("Alex", "Jones", "Alex.jones@alex.com"));

        assertThat(user.getId()).isNotNull();

        Location location = entityManager.persistAndFlush(new Location("Alex coffee", user));
        Location location1 = entityManager.persistAndFlush(new Location("Jane coffee", user));

        List<Location> locations = locationRepository.findAllByOwner_IdOrderByIdAsc(user.getId());

        assertThat(locations).hasSize(2);
        assertThat(locations.get(0).getName()).isEqualTo(location.getName());
        assertThat(locations.get(1).getName()).isEqualTo(location1.getName());
    }
}
