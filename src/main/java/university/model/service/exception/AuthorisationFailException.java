package university.model.service.exception;

public class AuthorisationFailException extends RuntimeException {

    private final int statusCode;

    public AuthorisationFailException(int statusCode) {
        this.statusCode = statusCode;
    }

    public AuthorisationFailException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
