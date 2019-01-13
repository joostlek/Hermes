package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.LocationNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DisplayName("Location not found advice")
@Tag("Advices")
class LocationNotFoundAdviceTest {
    @Test
    void testThrow() {
        LocationNotFoundAdvice locationNotFoundAdvice = new LocationNotFoundAdvice();
        LocationNotFoundException exception = new LocationNotFoundException(1L);
        try {
            throw exception;
        } catch (LocationNotFoundException ex) {
            assertThat(locationNotFoundAdvice.locationNotFoundHandler(ex).getMessage()).isEqualTo("Could not find location 1");
        }
    }
}