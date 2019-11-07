package university.model.service.validator;

public interface Validator<E> {
    void validate(E entity);
}
