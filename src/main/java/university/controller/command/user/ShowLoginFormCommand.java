package university.controller.command.user;

import university.controller.command.Command;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class ShowLoginFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagesConstant.AUTHORIZATION_PAGE;
    }

}
