package university.controller.command.admin;

import university.controller.command.Command;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class ShowAdminProfile implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagesConstant.ADMIN_PROFILE;
    }
}
