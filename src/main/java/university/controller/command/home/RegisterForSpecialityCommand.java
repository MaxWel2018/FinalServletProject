package university.controller.command.home;

import university.controller.command.Command;
import university.domain.Course;
import university.domain.ExamResult;
import university.domain.Speciality;
import university.domain.User;
import university.model.service.ResultService;
import university.model.service.UserService;
import university.model.service.exception.InCorrectDateRuntimeException;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class RegisterForSpecialityCommand implements Command {

    private final ResultService resultService;

    private final UserService userService;

    public RegisterForSpecialityCommand(ResultService resultService, UserService userService) {
        this.resultService = resultService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        final User user = (User) request.getSession().getAttribute("user");
        final Speciality spec = (Speciality) request.getSession().getAttribute("specialityFounded");
        saveCourses(request, user, spec.getRequiredCourses(),spec);
        userService.update(mapUser(user, spec));
        return PagesConstant.PROFILE_PAGE;
    }

    private void saveCourses(HttpServletRequest request, User user, List<Course> requiredCourses,Speciality speciality) {
        for (Course course : requiredCourses) {
            resultService.save(mapReqToResultExam(request, user, course,speciality));
        }
    }

    private User mapUser(User user, Speciality spec) {
        User userFromDatabase = userService.findById(user.getId());
        return User.newBuilder()
                .withSpeciality(spec)
                .withPassword(userFromDatabase.getPassword())
                .withId(user.getId())
                .withRole(user.getRole())
                .withFirstName(user.getFirstName())
                .withSecondName(user.getSecondName())
                .withEmail(user.getEmail())
                .build();
    }

    private ExamResult mapReqToResultExam(HttpServletRequest request, User user, Course course,Speciality speciality) {
        String date = request.getParameter(String.valueOf(course.getId()));
        if (!isWithInRange(Date.valueOf(date), speciality)) {
            throw new InCorrectDateRuntimeException("Date in correct");
        }
        LocalDate localDate = LocalDate.parse(date);

        return mapExamResult(user, course, localDate);
    }

    private boolean isWithInRange(Date testDate, Speciality speciality) {
        return !(testDate.before(Date.valueOf(speciality.getExamsStart())) || testDate.after(Date.valueOf(speciality.getExamsEnd())));
    }

    private ExamResult mapExamResult(User user, Course course, LocalDate date) {
        return ExamResult.newBuilder()
                .withUserId(user.getId())
                .withUser(user)
                .withCourse(course)
                .withCourse(course)
                .withDate(date)
                .build();
    }
}
