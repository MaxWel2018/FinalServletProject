package university.controller.command.admin;

import university.controller.command.Command;
import university.domain.ExamResult;
import university.model.service.CourseService;
import university.model.service.ResultService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class ShowUsersGradeCommand implements Command {
    private final CourseService courseService;
    private final ResultService resultService;

    public ShowUsersGradeCommand(CourseService courseService, ResultService resultService) {
        this.courseService = courseService;
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("allCourses", courseService.findAll());
        Integer courseSelectedId = Integer.valueOf(request.getParameter("courseSelected"));
        LocalDate examDate = LocalDate.parse(request.getParameter("data-exam"));
        List<ExamResult> allResultExamByCourseIdAndData = resultService.findAllByCourseIdAndData(courseSelectedId, examDate);

        request.setAttribute("allResult", allResultExamByCourseIdAndData);
        request.setAttribute("select", courseSelectedId);

        return PagesConstant.SET_GRADES_PAGE;
    }
}
