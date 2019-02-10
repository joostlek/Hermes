package nl.jtosti.hermes.company.exception;

public class LastUserException extends RuntimeException {
    public LastUserException() {
        super("Can't remove last user!");
    }
}
