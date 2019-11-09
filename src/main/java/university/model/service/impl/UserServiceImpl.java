package university.model.service.impl;

import org.apache.log4j.Logger;
import university.domain.SpecialityRequest;
import university.domain.User;
import university.model.dao.contract.ResultForSpecialityDao;
import university.model.dao.contract.UserDao;
import university.model.dao.entity.SpecialityRequestEntity;
import university.model.dao.entity.UserEntity;
import university.model.dao.exception.EntityNotFoundException;
import university.model.service.contract.UserService;
import university.model.service.exception.AuthorisationFailException;
import university.model.service.exception.EntityAlreadyExistException;
import university.model.service.mapper.SpecialityReqDomainMapper;
import university.model.service.mapper.UserMapper;
import university.model.service.validator.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private final UserDao userDao;
    private final Validator<User> validator;
    private final UserMapper userMapper;
    private final ResultForSpecialityDao resultForSpecialityDao;
    private final SpecialityReqDomainMapper specialityRequestEntities;

    public UserServiceImpl(UserDao userDao, Validator<User> validator, UserMapper userMapper, ResultForSpecialityDao resultForSpecialityDao, SpecialityReqDomainMapper specialityRequestEntities) {
        this.userDao = userDao;
        this.validator = validator;
        this.userMapper = userMapper;
        this.resultForSpecialityDao = resultForSpecialityDao;
        this.specialityRequestEntities = specialityRequestEntities;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(userMapper.mapUserEntityToUser(userDao.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("User with email: " + email + " Dont found "))));
    }

    @Override
    public User login(String email, String password) {
        return userDao.findByEmail(email)
                .map(userMapper::mapUserEntityToUser)
                .filter(x -> Objects.equals(x.getPassword(), password))
                .orElseThrow(() -> new AuthorisationFailException(401));
    }

    @Override
    public void register(User user) {
        validator.validate(user);
        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new EntityAlreadyExistException(401);
        }
        userDao.save(userMapper.mapUserToUserEntity(user));
    }

    @Override
    public Optional<User> findById(Integer id) {

        return Optional.ofNullable(userDao.findById(id)
                .map(userMapper::mapUserEntityToUser)
                .orElseThrow(() -> new EntityNotFoundException("User with id=  " + id + " Dont found ")));
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException();
    }



    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = userDao.findAll();
        return userEntities.isEmpty() ? Collections.emptyList() : userEntities
                .stream()
                .map(userMapper::mapUserEntityToUser)
                .collect(Collectors.toList());

    }

    @Override
    public List<SpecialityRequest> generateRating(Integer currentPage, Integer recordsPerPage, Integer specialityId) {
        Integer defaultStart = 0;
        Integer defaultRecordsPerPage = 10;

        if (currentPage <= 0 || recordsPerPage <= 0) {
            return returnRating(defaultRecordsPerPage, defaultStart, specialityId);

        } else {
            Integer start = currentPage * recordsPerPage - recordsPerPage;
            return returnRating(recordsPerPage, start, specialityId);
        }


    }

    private List<SpecialityRequest> returnRating(Integer recordsPerPage, Integer start, Integer specialityId) {
        List<SpecialityRequestEntity> requestEntities = resultForSpecialityDao.generateRating(start, recordsPerPage, specialityId);
        return requestEntities.isEmpty() ? Collections.emptyList() : requestEntities.stream()
                .map(specialityRequestEntities::mapSpecReqEntityToSpecReq)
                .collect(Collectors.toList());
    }

}
