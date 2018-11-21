package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.ScreenNotFoundException;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ScreenNotFoundAdviceTest {
    @Test
    public void testThrow() {
        ScreenNotFoundAdvice screenNotFoundAdvice = new ScreenNotFoundAdvice();
        ScreenNotFoundException exception = new ScreenNotFoundException(1L);
        try {
            throw exception;
        } catch (ScreenNotFoundException ex) {
            assertThat(screenNotFoundAdvice.screenNotFoundHandler(ex)).isEqualTo("Could not find screen 1");
        }
    }

}