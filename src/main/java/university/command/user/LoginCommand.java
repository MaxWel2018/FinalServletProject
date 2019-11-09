package university.command.user;

import university.command.Command;
import university.domain.User;
import university.model.service.contract.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email =  request.getParameter("email");
        String passwordEncode = (String) request.getAttribute("passwordEncode");
        final User user = userService.login(email, passwordEncode);

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("isLogin", true);

//        return "view/profile.jsp";
        return PagesConstant.HOME_PAGE;
    }

}
