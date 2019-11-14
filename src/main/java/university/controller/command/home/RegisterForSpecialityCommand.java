package university.controller.command.home;

import university.controller.command.Command;
import university.domain.Course;
import university.domain.ExamResult;
import university.domain.Speciality;
import university.domain.User;
import university.model.service.ResultService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegisterForSpecialityCommand implements Command {

    private final ResultService resultService;

    public RegisterForSpecialityCommand(ResultService resultService) {
        this.resultService = resultService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Speciality spec = (Speciality) request.getSession().getAttribute("specialityFounded");
        List<Course> requiredCourses = spec.getRequiredCourses();
        for (Course course : requiredCourses) {
            resultService.save(mapReqToResultExam(request, user, course));
        }
        return PagesConstant.REGISTRATION_EXAM_PAGE;
    }



    private ExamResult mapReqToResultExam(HttpServletRequest request, User user, Course course) {
        String parameter = request.getParameter(String.valueOf(course.getId()));
        return ExamResult.newBuilder()
                .withUserId(user.getId())
                .withCourse(course)
                .withDate(LocalDate.parse(parameter))
                .build();
    }
}
