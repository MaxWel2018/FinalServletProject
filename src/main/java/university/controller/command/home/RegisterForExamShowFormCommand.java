package university.controller.command.home;

import university.controller.command.Command;
import university.domain.User;
import university.model.service.Page;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class RegisterForExamShowFormCommand implements Command {

    private final UserService userService;

    public RegisterForExamShowFormCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = userService.findById(((User) request.getSession().getAttribute("user")).getId());
        if (user.getSpeciality() == null) {
            return PagesConstant.REGISTRATION_EXAM_PAGE;
        }else{
            request.setAttribute("isRegistered",true);
            return PagesConstant.PROFILE_PAGE;
        }


    }
}
