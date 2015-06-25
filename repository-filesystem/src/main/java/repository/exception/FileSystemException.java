package repository.exception;

public class FileSystemException extends Exception {

    private static final long serialVersionUID = 3959814376569082473L;

    public FileSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
