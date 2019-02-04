package nl.jtosti.hermes.util;

public class ErrorDTO {
    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
