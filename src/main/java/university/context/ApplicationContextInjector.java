package university.context;

import university.command.Command;
import university.command.user.LoginCommand;
import university.command.user.RegisterCommand;
import university.domain.User;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.CourseDao;
import university.model.dao.contract.ResultForSpecialityDao;
import university.model.dao.contract.SpecialityDao;
import university.model.dao.contract.UserDao;
import university.model.dao.impl.CourseDaoImpl;
import university.model.dao.impl.ResultForSpecialityDaoImpl;
import university.model.dao.impl.SpecialityDaoImpl;
import university.model.dao.impl.UserDaoImpl;
import university.model.dao.mapper.SpecialityReqMapper;
import university.model.service.contract.SpecialityService;
import university.model.service.contract.UserService;
import university.model.service.impl.SpecialityServiceImpl;
import university.model.service.impl.UserServiceImpl;
import university.model.service.mapper.CourseMapperDomain;
import university.model.service.mapper.SpecialityDomainMapper;
import university.model.service.mapper.SpecialityReqDomainMapper;
import university.model.service.mapper.UserMapper;
import university.model.service.validator.UserValidator;
import university.model.service.validator.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {
    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL);

    private static final UserMapper USER_MAPPER = new UserMapper();

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final SpecialityDao SPECIALITY_DAO = new SpecialityDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CourseDao COURSE_DAO = new CourseDaoImpl(HIKARI_CONNECTION_POOL);

    private static final SpecialityReqDomainMapper SPECIALITY_REQ_DOMAIN_MAPPER = new SpecialityReqDomainMapper();

    private static final SpecialityReqMapper SPECIALITY_REQ_MAPPER = new SpecialityReqMapper();

    private static final ResultForSpecialityDao RESULT_FOR_SPECIALITY_DAO = new ResultForSpecialityDaoImpl(HIKARI_CONNECTION_POOL, SPECIALITY_REQ_MAPPER);

    private static final SpecialityDomainMapper SPECIALITY_DOMAIN_MAPPER = new SpecialityDomainMapper();

    private static final CourseMapperDomain COURSE_MAPPER = new CourseMapperDomain();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, USER_MAPPER, RESULT_FOR_SPECIALITY_DAO, SPECIALITY_REQ_DOMAIN_MAPPER);

    private static final SpecialityService SPECIALITY_SERVICE = new SpecialityServiceImpl(SPECIALITY_DAO, SPECIALITY_DOMAIN_MAPPER, COURSE_MAPPER, RESULT_FOR_SPECIALITY_DAO);

    private static ApplicationContextInjector applicationContextInjector;

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);

    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private ApplicationContextInjector() {

    }

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        userCommandNameToCommand.put("login", LOGIN_COMMAND);

        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    public HikariConnectionPool getHikariConnectionPool() {
        return HIKARI_CONNECTION_POOL;
    }

    public SpecialityService getSpecialityService() {
        return SPECIALITY_SERVICE;
    }


    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public  UserService getUserService() {
        return USER_SERVICE;
    }

    public UserMapper getUserMapper() {
        return USER_MAPPER;
    }

    public Validator<User> getUserValidator() {
        return USER_VALIDATOR;
    }

    public SpecialityReqMapper getSpecialityReqMapper() {
        return SPECIALITY_REQ_MAPPER;
    }

    public CourseMapperDomain getCourseMapper() {
        return COURSE_MAPPER;
    }

    public Command getLoginCommand() {
        return LOGIN_COMMAND;
    }

    public Command getRegisterCommand() {
        return REGISTER_COMMAND;
    }

    public Map<String, Command> getUserCommandNameToCommand() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }
}




