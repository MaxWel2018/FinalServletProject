package university.context;

import university.controller.command.Command;
import university.controller.command.home.RegisterForSpecialityCommand;
import university.controller.command.home.SpecialityInfoCommand;
import university.controller.command.result.ShowSpecForRatingCommand;
import university.controller.command.user.LoginCommand;
import university.controller.command.user.RegisterCommand;
import university.domain.User;
import university.model.dao.*;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.impl.*;
import university.model.dao.mapper.SpecialityReqResultSetMapper;
import university.model.mapper.*;
import university.model.service.CourseService;
import university.model.service.ResultService;
import university.model.service.SpecialityService;
import university.model.service.UserService;
import university.model.service.impl.CourseServiceImpl;
import university.model.service.impl.ResultServiceImpl;
import university.model.service.impl.SpecialityServiceImpl;
import university.model.service.impl.UserServiceImpl;
import university.model.validator.UserValidator;
import university.model.validator.Validator;

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

    private static final ExamResultDao EXAM_RESULT_DAO = new ExamResultDaoImpl(HIKARI_CONNECTION_POOL);

    private static final SpecialityReqMapper SPECIALITY_REQ_MAPPER = new SpecialityReqMapper();

    private static final SpecialityReqResultSetMapper SPECIALITY_REQ_RESULT_SET_MAPPER = new SpecialityReqResultSetMapper();

    private static final SpecialityMapper SPECIALITY_DOMAIN_MAPPER = new SpecialityMapper();

    private static final CourseMapper COURSE_MAPPER = new CourseMapper();


    private static final CourseService COURSE_SERVICE = new CourseServiceImpl(COURSE_DAO, COURSE_MAPPER);


    private static final ResultForSpecialityDao RESULT_FOR_SPECIALITY_DAO = new ResultForSpecialityDaoImpl(HIKARI_CONNECTION_POOL, SPECIALITY_REQ_RESULT_SET_MAPPER);

    private static final SpecialityService SPECIALITY_SERVICE = new SpecialityServiceImpl(SPECIALITY_DAO, SPECIALITY_DOMAIN_MAPPER, COURSE_MAPPER, RESULT_FOR_SPECIALITY_DAO);

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, USER_MAPPER);

    private static final ExamResultMapper EXAM_RESULT_MAPPER = new ExamResultMapper(USER_SERVICE, COURSE_SERVICE);

    private static final ResultService RESULT_SERVICE = new ResultServiceImpl(RESULT_FOR_SPECIALITY_DAO, EXAM_RESULT_DAO, SPECIALITY_REQ_MAPPER, EXAM_RESULT_MAPPER);

    private static ApplicationContextInjector applicationContextInjector;

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);

    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Command RATING_COMMAND = new ShowSpecForRatingCommand(RESULT_SERVICE, SPECIALITY_SERVICE);

    private static final Command SHOW_SPECIALITY_INFO = new SpecialityInfoCommand(SPECIALITY_SERVICE);

    private static final Command EXAM_REGISTRATION = new RegisterForSpecialityCommand(RESULT_SERVICE);

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private static final Map<String, Command> HOME_COMMAND_NAME_TO_COMMAND = initHomeCommand();

    private static final Map<String, Command> RATING_COMMAND_NAME_TO_COMMAND = initRatingCommand();

    private ApplicationContextInjector() {

    }

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    private static Map<String, Command> initHomeCommand() {
        Map<String, Command> homeCommandNameToCommand = new HashMap<>();
        homeCommandNameToCommand.put("info", SHOW_SPECIALITY_INFO);
        homeCommandNameToCommand.put("exam-registration", EXAM_REGISTRATION);
        return Collections.unmodifiableMap(homeCommandNameToCommand);
    }

    private static Map<String, Command> initRatingCommand() {
        Map<String, Command> mapRatingCommandToCommand = new HashMap<>();
        mapRatingCommandToCommand.put("show-speciality", RATING_COMMAND);
        return Collections.unmodifiableMap(mapRatingCommandToCommand);
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

    public Map<String, Command> getHomeCommandNameToCommand() {
        return HOME_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public UserMapper getUserMapper() {
        return USER_MAPPER;
    }

    public Validator<User> getUserValidator() {
        return USER_VALIDATOR;
    }

    public SpecialityReqResultSetMapper getSpecialityReqMapper() {
        return SPECIALITY_REQ_RESULT_SET_MAPPER;
    }

    public CourseMapper getCourseMapper() {
        return COURSE_MAPPER;
    }

    public Command getLoginCommand() {
        return LOGIN_COMMAND;
    }

    public Command getRegisterCommand() {
        return REGISTER_COMMAND;
    }

    public Command getRatingCommand() {
        return RATING_COMMAND;
    }

    public Map<String, Command> getRatingCommandNameToCommand() {
        return RATING_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getUserCommandNameToCommand() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }
}




