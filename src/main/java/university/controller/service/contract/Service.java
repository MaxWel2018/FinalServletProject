package university.controller.service.contract;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Service<T> {
    void register(T t);

    Optional<T> findById(Integer id);

    void update(T entity);

    T deleteById(Integer id);

    List<T> findAll();

    void deleteAllByIds(Set<Integer> integers);

}
