package university.controller.command.user.profile;

import university.controller.command.Command;
import university.domain.Course;
import university.domain.ExamResult;
import university.domain.Speciality;
import university.domain.User;
import university.model.service.ResultService;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AboutUserCommand implements Command {
    private final ResultService resultService;

    private final UserService userService;


    public AboutUserCommand(ResultService resultService, UserService userService) {
        this.resultService = resultService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final User user = (User) request.getSession().getAttribute("user");
        final Integer id = user.getId();
        final User updatedUser = userService.findById(id);
        final Speciality speciality = updatedUser.getSpeciality();

        if (speciality != null) {
            List<Course> requiredCourses = speciality.getRequiredCourses();
            List<ExamResult> examResults = new ArrayList<>();
            for (Course requiredCourse : requiredCourses) {
                examResults.add(resultService.findExamResultByUserIdAndCourseId(id, requiredCourse.getId()));
            }
            request.getSession().setAttribute("examResults", examResults);
            request.getSession().setAttribute("userSpec", speciality);
        }

        return PagesConstant.PROFILE_ABOUT_PAGE;
    }
}
