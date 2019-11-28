package university.model.service.exception;

public class AuthorisationFailException extends RuntimeException {
    public AuthorisationFailException(String message) {
        super(message);
    }
}
