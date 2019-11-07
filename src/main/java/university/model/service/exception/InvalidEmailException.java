package university.model.service.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(Throwable cause) {
        super(cause);
    }

    public InvalidEmailException() {

    }
}
