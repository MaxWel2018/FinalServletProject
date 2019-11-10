package university.model.service;

import university.domain.SpecialityRequest;
import university.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    Optional<User> findByEmail(String email);

    User login(String email, String password);


    void register(User user);

    Optional<User> findById(Integer id);

    void update(User entity);

    List<User> findAll();

    List<SpecialityRequest> generateRating(Integer currentPage, Integer recordsPerPage, Integer specialityId);

}
