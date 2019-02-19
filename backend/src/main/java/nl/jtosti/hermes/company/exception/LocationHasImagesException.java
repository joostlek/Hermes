package nl.jtosti.hermes.company.exception;

public class LocationHasImagesException extends RuntimeException {
    public LocationHasImagesException(String locationName) {
        super(String.format("%s still has images left", locationName));
    }
}
