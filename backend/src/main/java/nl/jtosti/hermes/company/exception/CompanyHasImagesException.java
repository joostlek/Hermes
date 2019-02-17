package nl.jtosti.hermes.company.exception;

public class CompanyHasImagesException extends RuntimeException {
    public CompanyHasImagesException(String companyName) {
        super(String.format("%s still has images left", companyName));
    }
}
