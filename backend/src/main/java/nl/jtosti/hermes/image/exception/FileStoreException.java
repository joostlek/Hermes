package nl.jtosti.hermes.image.exception;

public class FileStoreException extends RuntimeException {
    public FileStoreException(String place) {
        super(String.format("File store exception at %s", place));
    }
}
