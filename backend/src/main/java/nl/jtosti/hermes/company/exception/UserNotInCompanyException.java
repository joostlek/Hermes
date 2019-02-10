package nl.jtosti.hermes.company.exception;

public class UserNotInCompanyException extends RuntimeException {
    public UserNotInCompanyException() {
        super("User is not in current company");
    }
}
