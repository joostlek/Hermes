package nl.jtosti.hermes.exceptions;

public class FileStoreException extends RuntimeException {
    public FileStoreException(String place) {
        super(String.format("File store exception at %s", place));
    }
}
