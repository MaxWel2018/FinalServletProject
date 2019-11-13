package university.controller.command.user;

import university.controller.command.Command;
import university.domain.User;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email");
        String passwordEncode = (String) request.getAttribute("passwordEncode");
        final User user = userService.login(email, passwordEncode);

        request.getSession().setAttribute("user", mapUserToUserForInfoWithOutPassword(user));
        request.getSession().setAttribute("isLogin", true);

        return PagesConstant.HOME_PAGE;
    }

    private User mapUserToUserForInfoWithOutPassword(User user) {

        return User.newBuilder()
                .withEmail(user.getEmail())
                .withFirstName(user.getFirstName())
                .withId(user.getId())
                .withSecondName(user.getSecondName())
                .withRole(user.getRole())
                .build();

    }

}
