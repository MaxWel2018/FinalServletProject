package university.model.validator;

public interface Validator<E> {
    void validate(E entity);
}
