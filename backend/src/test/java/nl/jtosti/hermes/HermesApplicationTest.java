package nl.jtosti.hermes;

import nl.jtosti.hermes.controllers.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Application")
@Tag("Application")
class HermesApplicationTest {

    @Autowired
    private UserController userController;

    @Test
    @DisplayName("Test context loading")
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    @DisplayName("Test startup")
    void main() {
        HermesApplication.main(new String[]{});
    }

}
