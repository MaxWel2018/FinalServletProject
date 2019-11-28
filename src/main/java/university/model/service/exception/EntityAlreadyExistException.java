package university.model.service.exception;

public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException(String msg) {
        super(msg);
    }

}
