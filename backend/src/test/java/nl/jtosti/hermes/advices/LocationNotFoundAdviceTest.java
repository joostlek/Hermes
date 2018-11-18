package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.LocationNotFoundException;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LocationNotFoundAdviceTest {
    @Test
    public void testThrow() {
        LocationNotFoundAdvice locationNotFoundAdvice = new LocationNotFoundAdvice();
        LocationNotFoundException exception = new LocationNotFoundException(1L);
        try {
            throw exception;
        } catch (LocationNotFoundException ex) {
            assertThat(locationNotFoundAdvice.locationNotFoundHandler(ex)).isEqualTo("Could not find location 1");
        }
    }
}