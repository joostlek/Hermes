package nl.jtosti.hermes.location;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Location Repository")
@Tag("Repositories")
public class LocationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocationRepository locationRepository;

    @MockBean
    private StorageServiceInterface storageService;

    @Test
    public void whenGivenUserId_thenReturnLocations() {
        User user = entityManager.persistAndFlush(new User("Alex", "Jones", "Alex.jones@alex.com", ""));
        Company company = entityManager.persistAndFlush(new Company("", "", "", "", "", "", ""));

        assertThat(user.getId()).isNotNull();

        Location location = entityManager.persistAndFlush(new Location("Alex coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company));
        Location location1 = entityManager.persistAndFlush(new Location("Jane coffee", "Alexstreet", "1", "1234AB", "Coffee", "land", company));

        List<Location> locations = locationRepository.findAll();

        assertThat(locations).hasSize(2);
        assertThat(locations.get(0).getName()).isEqualTo(location.getName());
        assertThat(locations.get(1).getName()).isEqualTo(location1.getName());
    }
}
