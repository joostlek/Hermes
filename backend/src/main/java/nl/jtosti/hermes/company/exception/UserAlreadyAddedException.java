package nl.jtosti.hermes.company.exception;

public class UserAlreadyAddedException extends RuntimeException {
    public UserAlreadyAddedException() {
        super("User already added to this company");
    }
}
