package nl.jtosti.hermes.location.exception;

public class LocationAlreadyAddedException extends RuntimeException {
    public LocationAlreadyAddedException() {
        super("Location already added");
    }
}
