package university.model.service.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateException() {
        super();
    }

    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(Throwable cause) {
        super(cause);
    }
}
