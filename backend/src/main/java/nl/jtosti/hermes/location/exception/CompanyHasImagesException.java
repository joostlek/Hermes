package nl.jtosti.hermes.location.exception;

public class CompanyHasImagesException extends RuntimeException {
    public CompanyHasImagesException(String companyName) {
        super(String.format("%s still has images left", companyName));
    }
}
