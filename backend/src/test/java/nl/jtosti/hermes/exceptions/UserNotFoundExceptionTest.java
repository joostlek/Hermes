package nl.jtosti.hermes.exceptions;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserNotFoundExceptionTest {

    @Test
    void testThrow() {
        UserNotFoundException exception = new UserNotFoundException(1L);
        try {
            throw exception;
        } catch (UserNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find user 1");
        }
    }

}
