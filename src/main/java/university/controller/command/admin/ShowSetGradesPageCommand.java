package university.controller.command.admin;

import university.controller.command.Command;
import university.model.service.CourseService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class ShowSetGradesPageCommand implements Command {
    private final CourseService courseService;

    public ShowSetGradesPageCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("allCourses", courseService.findAll());
        return PagesConstant.SET_GRADES_PAGE;
    }
}
