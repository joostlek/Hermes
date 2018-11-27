package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.ScreenNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DisplayName("Screen not found advice")
@Tag("Advices")
class ScreenNotFoundAdviceTest {
    @Test
    void testThrow() {
        ScreenNotFoundAdvice screenNotFoundAdvice = new ScreenNotFoundAdvice();
        ScreenNotFoundException exception = new ScreenNotFoundException(1L);
        try {
            throw exception;
        } catch (ScreenNotFoundException ex) {
            assertThat(screenNotFoundAdvice.screenNotFoundHandler(ex)).isEqualTo("Could not find screen 1");
        }
    }

}