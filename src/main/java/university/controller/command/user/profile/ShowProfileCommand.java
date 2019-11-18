package university.controller.command.user.profile;

import university.controller.command.Command;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class ShowProfileCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {

        return PagesConstant.PROFILE_PAGE;
    }
}
