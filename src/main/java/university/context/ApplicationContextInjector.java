package university.context;

import university.command.Command;
import university.command.user.LoginCommand;
import university.command.user.LogoutCommand;
import university.command.user.RegisterCommand;
import university.model.service.contract.SpecialityService;
import university.model.service.contract.UserService;
import university.model.service.impl.SpecialityServiceImpl;
import university.model.service.impl.UserServiceImpl;
import university.model.service.mapper.UserMapper;
import university.model.service.validator.UserValidator;
import university.model.service.validator.Validator;
import university.domain.User;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.*;
import university.model.dao.impl.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {
    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL);

    private static final UserMapper USER_MAPPER = new UserMapper();

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final SpecialityDao SPECIALITY_DAO = new SpecialityDaoImpl(HIKARI_CONNECTION_POOL);

    private static final SpecialityCourseDao SPECIALITY_COURSE_DAO = new SpecialityCourseDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CourseDao COURSE_DAO = new CourseDaoImpl(HIKARI_CONNECTION_POOL);

    private static final ResultForSpecialityDao RESULT_FOR_SPECIALITY_DAO = new ResultForSpecialityDaoImpl(HIKARI_CONNECTION_POOL);

    private static final SpecialityService SPECIALITY_SERVICE = new SpecialityServiceImpl(SPECIALITY_DAO); // пофиксить

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, USER_MAPPER);

    private static ApplicationContextInjector applicationContextInjector;

    private static final Command LOGOUT_COMMAND = new LogoutCommand();

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);

    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private ApplicationContextInjector() {

    }

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
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
}




