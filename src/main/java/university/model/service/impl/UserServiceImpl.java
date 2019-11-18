package university.model.service.impl;

import org.apache.log4j.Logger;
import university.domain.User;
import university.model.dao.UserDao;
import university.model.dao.entity.UserEntity;
import university.model.dao.exception.EntityNotFoundException;
import university.model.mapper.UserMapper;
import university.model.service.UserService;
import university.model.service.exception.AuthorisationFailException;
import university.model.service.exception.EntityAlreadyExistException;
import university.model.validator.Validator;

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


    public UserServiceImpl(UserDao userDao, Validator<User> validator, UserMapper userMapper) {
        this.userDao = userDao;
        this.validator = validator;
        this.userMapper = userMapper;


    }


    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(userDao.findByEmail(email).map(userMapper::mapEntityToDomain).orElseThrow(() ->
                new EntityNotFoundException("User with email: " + email + " Dont found ")));
    }

    @Override
    public User login(String email, String password) {
        return userDao.findByEmail(email)
                .map(userMapper::mapEntityToDomain)
                .filter(x -> Objects.equals(x.getPassword(), password))
                .orElseThrow(() -> new AuthorisationFailException(401));
    }

    @Override
    public void register(User user) {
        validator.validate(user);
        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new EntityAlreadyExistException(401);
        }
        userDao.save(userMapper.mapDomainToEntity(user));
    }

    @Override
    public User findById(Integer id) {

        return userDao.findById(id)
                .map(userMapper::mapEntityToDomain)
                .orElseThrow(() -> new EntityNotFoundException("User with id=  " + id + " Dont found "));
    }

    @Override
    public void update(User entity) {
        userDao.update(userMapper.mapDomainToEntity(entity));
    }


    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = userDao.findAll();
        return userEntities.isEmpty() ? Collections.emptyList() : userEntities
                .stream()
                .map(userMapper::mapEntityToDomain)
                .collect(Collectors.toList());

    }


}
