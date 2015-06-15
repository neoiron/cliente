package domain.exception;

public class MunicipioException extends Exception {

    private static final long serialVersionUID = -2194177049299589050L;

    public MunicipioException(String message, Throwable cause) {
        super(message, cause);
    }

    public MunicipioException(String message) {
        super(message);
    }

    public MunicipioException(Throwable cause) {
        super(cause);
    }
}
