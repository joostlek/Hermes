package nl.jtosti.hermes.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super(String.format("Could not find location %s", id));
    }
}
