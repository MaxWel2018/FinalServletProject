package university.controller.command.home;

import university.controller.command.Command;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class RegisterForExamShowFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PagesConstant.REGISTRATION_EXAM_PAGE;
    }
}
