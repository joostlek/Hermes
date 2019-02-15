package nl.jtosti.hermes.location.exception;

public class LocationNotSelectedException extends RuntimeException {
    public LocationNotSelectedException() {
        super("There is no location selected");
    }
}
