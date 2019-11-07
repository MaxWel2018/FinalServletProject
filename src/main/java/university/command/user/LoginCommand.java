package university.command.user;

import university.command.Command;
import university.model.service.PasswordInCode;
import university.model.service.contract.UserService;
import university.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String email =  request.getParameter("email");
        String passwordEncode = (String) request.getAttribute("passwordEncode");
        final User user = userService.login(email, passwordEncode);

        session.setAttribute("user", user);

//        return "view/profile.jsp";
        return PagesConstant.HOME_PAGE;
    }

}
