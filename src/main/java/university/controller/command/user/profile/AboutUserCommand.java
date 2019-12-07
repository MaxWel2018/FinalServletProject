package university.controller.command.user.profile;

import university.controller.command.Command;
import university.domain.Course;
import university.domain.ExamResult;
import university.domain.Speciality;
import university.domain.User;
import university.model.service.ResultForSpecService;
import university.model.service.ResultService;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AboutUserCommand implements Command {
    private final ResultService resultService;

    private final ResultForSpecService resultForSpecService;

    private final UserService userService;

    public AboutUserCommand(ResultService resultService, ResultForSpecService resultForSpecService, UserService userService) {
        this.resultService = resultService;
        this.resultForSpecService = resultForSpecService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final User user = (User) request.getSession().getAttribute("user");
        final Integer userId = user.getId();
        final User updatedUser = userService.findById(userId);
        final Speciality speciality = updatedUser.getSpeciality();
        setExamResult(request, userId, speciality);

        request.getSession().setAttribute("finalResult",resultForSpecService.findByUserId(userId));

        return PagesConstant.PROFILE_ABOUT_PAGE;
    }

    private void setExamResult(HttpServletRequest request, Integer id, Speciality speciality) {
        if (speciality != null) {
            List<Course> requiredCourses = speciality.getRequiredCourses();
            List<ExamResult> examResults = new ArrayList<>();
            for (Course requiredCourse : requiredCourses) {
                examResults.add(resultService.findExamResultByUserIdAndCourseId(id, requiredCourse.getId()));
            }
            request.getSession().setAttribute("userSpec", speciality);
            request.getSession().setAttribute("examResults", examResults);
        }
    }
}
