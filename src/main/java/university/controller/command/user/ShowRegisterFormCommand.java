package university.controller.command.user;

import university.controller.command.Command;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class ShowRegisterFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PagesConstant.REGISTRATION_PAGE;
    }
}
