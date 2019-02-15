package nl.jtosti.hermes.location.exception;

public class LocationIsFromCompanyException extends RuntimeException {
    public LocationIsFromCompanyException() {
        super("Selected location is from this company!");
    }
}
