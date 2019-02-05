package nl.jtosti.hermes.company.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long id) {
        super(String.format("Could not find company %s", id));
    }
}
