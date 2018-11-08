package nl.jtosti.hermes.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ScreenRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ScreenRepository screenRepository;

    @Test
    public void test() {

    }


}