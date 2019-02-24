package nl.jtosti.hermes.user.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super(String.format("%s is already in use", email));
    }
}
