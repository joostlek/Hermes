package nl.jtosti.hermes.advices;


import nl.jtosti.hermes.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class UserNotFoundAdviceTest {
    @Test
    void testThrow() {
        UserNotFoundAdvice userNotFoundAdvice = new UserNotFoundAdvice();
        UserNotFoundException exception = new UserNotFoundException(1L);
        try {
            throw exception;
        } catch (UserNotFoundException ex) {
            assertThat(userNotFoundAdvice.userNotFoundHandler(ex)).isEqualTo("Could not find user 1");
        }
    }
}