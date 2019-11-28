package university.controller.command.user;

import university.controller.command.Command;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.invalidate();
        return PagesConstant.AUTHORIZATION_PAGE;
    }
}
