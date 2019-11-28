package university.context;

import university.controller.command.Command;
import university.controller.command.admin.*;
import university.controller.command.home.RegisterForExamShowFormCommand;
import university.controller.command.home.RegisterForSpecialityCommand;
import university.controller.command.home.SpecialityInfoCommand;
import university.controller.command.result.ShowSpecForRatingCommand;
import university.controller.command.user.*;
import university.controller.command.user.profile.AboutUserCommand;
import university.controller.command.user.profile.ShowProfileCommand;
import university.domain.User;
import university.model.dao.*;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.impl.*;
import university.model.dao.mapper.CourseEntityMapper;
import university.model.dao.mapper.SpecialityEntityMapper;
import university.model.dao.mapper.UserEntityMapper;
import university.model.dao.mapper.RoleEntityMapper;
import university.model.dao.mapper.impl.CourseEntityMapperImpl;
import university.model.dao.mapper.impl.RoleEntityMapperImpl;
import university.model.dao.mapper.impl.SpecialityEntityMapperImpl;
import university.model.dao.mapper.impl.UserEntityMapperImpl;
import university.model.mapper.*;
import university.model.service.*;
import university.model.service.impl.*;
import university.model.validator.UserValidator;
import university.model.validator.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {

    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final SpecialityDao SPECIALITY_DAO = new SpecialityDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CourseDao COURSE_DAO = new CourseDaoImpl(HIKARI_CONNECTION_POOL);

    private static final SpecialityReqMapper SPECIALITY_REQ_MAPPER = new SpecialityReqMapper();

    private static final SpecialityMapper SPECIALITY_DOMAIN_MAPPER = new SpecialityMapper();

    private static final CourseMapper COURSE_MAPPER = new CourseMapper();

    private static final CourseEntityMapper COURSE_ENTITY_MAPPER = new CourseEntityMapperImpl();

    private static final CourseService COURSE_SERVICE = new CourseServiceImpl(COURSE_DAO, COURSE_MAPPER);

    private static final ResultForSpecialityDao RESULT_FOR_SPECIALITY_DAO = new ResultForSpecialityDaoImpl(HIKARI_CONNECTION_POOL);

    private static final SpecialityService SPECIALITY_SERVICE = new SpecialityServiceImpl(SPECIALITY_DAO, SPECIALITY_DOMAIN_MAPPER,  RESULT_FOR_SPECIALITY_DAO);

    private static final SpecialityMapper SPECIALITY_MAPPER = new SpecialityMapper();

    private static final UserMapper USER_MAPPER = new UserMapper(SPECIALITY_MAPPER);

    private static final SpecialityEntityMapper SPECIALITY_ENTITY_MAPPER = new SpecialityEntityMapperImpl();

    private static final RoleEntityMapper ROLE_ENTITY_MAPPER = new RoleEntityMapperImpl();

    private static final UserEntityMapper USER_ENTITY_MAPPER = new UserEntityMapperImpl(SPECIALITY_ENTITY_MAPPER, ROLE_ENTITY_MAPPER);

    private static final ExamResultDao EXAM_RESULT_DAO = new ExamResultDaoImpl(HIKARI_CONNECTION_POOL, USER_ENTITY_MAPPER, COURSE_ENTITY_MAPPER);

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL, USER_ENTITY_MAPPER);

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, USER_MAPPER);

    private static final ExamResultMapper EXAM_RESULT_MAPPER = new ExamResultMapper(COURSE_MAPPER,USER_MAPPER);

    private static final ResultService RESULT_SERVICE = new ResultServiceImpl(RESULT_FOR_SPECIALITY_DAO, EXAM_RESULT_DAO, SPECIALITY_REQ_MAPPER, EXAM_RESULT_MAPPER);

    private static final ResultForSpecService RESULT_FOR_SPEC_SERVICE = new ResultForSpecServiceImpl(RESULT_FOR_SPECIALITY_DAO, SPECIALITY_REQ_MAPPER);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);

    private static final Command LOG_OUT_COMMAND = new LogOutCommand();

    private static final Command SHOW_ENROLL_COMMAND = new ShowEnrollCommand(SPECIALITY_SERVICE);

    private static final Command ENROLL_USERS_COMMAND = new EnrollUsersCommand(RESULT_FOR_SPEC_SERVICE, SPECIALITY_SERVICE);

    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Command RATING_COMMAND = new ShowSpecForRatingCommand(SPECIALITY_SERVICE, RESULT_FOR_SPEC_SERVICE);

    private static final Command SHOW_SPECIALITY_INFO = new SpecialityInfoCommand(SPECIALITY_SERVICE);

    private static final Command SHOW_PROFILE_COMMAND = new ShowProfileCommand();

    private static final Command ABOUT_USER_COMMAND = new AboutUserCommand(RESULT_SERVICE, USER_SERVICE);

    private static final Command EXAM_REGISTRATION = new RegisterForSpecialityCommand(RESULT_SERVICE, USER_SERVICE);

    private static final Command REGISTER_FORM_FOR_EXAM = new RegisterForExamShowFormCommand();

    private static final Command REGISTER_FORM = new ShowRegisterFormCommand();

    private static final Command AUTH_FORM = new ShowLoginFormCommand();

    private static final Command SHOW_ADMIN_PROFILE_COMMAND = new ShowAdminProfile();

    private static final Command UPDATE_GRADE_COMMAND = new UpDateGradesCommand(RESULT_SERVICE);

    private static final Command UPDATE_RATING_COMMAND = new UpdateRatingCommand(RESULT_SERVICE, RESULT_FOR_SPEC_SERVICE, USER_SERVICE);

    private static final Command SHOW_SET_GRADES_PAGE_COMMAND = new ShowSetGradesPageCommand(COURSE_SERVICE);

    private static final Command SET_GRADES_COMMAND = new ShowUsersGradeCommand(COURSE_SERVICE, RESULT_SERVICE);

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private static final Map<String, Command> HOME_COMMAND_NAME_TO_COMMAND = initHomeCommand();

    private static final Map<String, Command> RATING_COMMAND_NAME_TO_COMMAND = initRatingCommand();

    private static final Map<String, Command> USER_PROFILE_COMMAND_NAME_TO_COMMAND = initUserProfileCommand();

    private static final Map<String, Command> ADMIN_COMMAND_NAME_TO_COMMAND = initAdminCommand();

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {

    }

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        userCommandNameToCommand.put("show-auth", AUTH_FORM);
        userCommandNameToCommand.put("show-register", REGISTER_FORM);
        userCommandNameToCommand.put("logout", LOG_OUT_COMMAND);
        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    private static Map<String, Command> initAdminCommand() {
        Map<String, Command> adminCommandNameToCommand = new HashMap<>();
        adminCommandNameToCommand.put("show-profile", SHOW_ADMIN_PROFILE_COMMAND);
        adminCommandNameToCommand.put("set-grades", SET_GRADES_COMMAND);
        adminCommandNameToCommand.put("show-set-grades", SHOW_SET_GRADES_PAGE_COMMAND);
        adminCommandNameToCommand.put("update-grade", UPDATE_GRADE_COMMAND);
        adminCommandNameToCommand.put("update-rating", UPDATE_RATING_COMMAND);
        adminCommandNameToCommand.put("show-enroll", SHOW_ENROLL_COMMAND);
        adminCommandNameToCommand.put("enroll", ENROLL_USERS_COMMAND);

        return Collections.unmodifiableMap(adminCommandNameToCommand);
    }

    private static Map<String, Command> initUserProfileCommand() {
        Map<String, Command> userProfileCommandNameToCommand = new HashMap<>();
        userProfileCommandNameToCommand.put("show-profile", SHOW_PROFILE_COMMAND);
        userProfileCommandNameToCommand.put("about", ABOUT_USER_COMMAND);
        return Collections.unmodifiableMap(userProfileCommandNameToCommand);
    }

    private static Map<String, Command> initHomeCommand() {
        Map<String, Command> homeCommandNameToCommand = new HashMap<>();
        homeCommandNameToCommand.put("info", SHOW_SPECIALITY_INFO);
        homeCommandNameToCommand.put("exam-registration", EXAM_REGISTRATION);
        homeCommandNameToCommand.put("show-form", REGISTER_FORM_FOR_EXAM);
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

    public Map<String, Command> getAdminCommand() {
        return ADMIN_COMMAND_NAME_TO_COMMAND;
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

    public ResultForSpecService getResultForSpecService() {
        return RESULT_FOR_SPEC_SERVICE;
    }

    public Map<String, Command> getRatingCommandNameToCommand() {
        return RATING_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getUserProfileCommand() {
        return USER_PROFILE_COMMAND_NAME_TO_COMMAND;
    }
}




