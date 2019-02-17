package nl.jtosti.hermes.location.exception;

public class CompanyNotAdvertisingException extends RuntimeException {
    public CompanyNotAdvertisingException(String companyName, String locationName) {
        super(String.format("%s is not advertising at %s", companyName, locationName));
    }
}
