package university.controller.command.user;

import university.controller.command.Command;
import university.domain.User;
import university.model.service.UserService;
import university.model.service.exception.AuthorisationFailException;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("authFailed", false);
        final String email = request.getParameter("email");
        String passwordEncode = (String) request.getSession().getAttribute("passwordEncode");
        User user;

        try {
            user = userService.login(email, passwordEncode);
            request.getSession().setAttribute("user", mapUserToUserForInfoWithOutPassword(user));
            request.getSession().setAttribute("isLogin", true);
        } catch (AuthorisationFailException e) {
            return reAuth(request);
        }
        if ("ADMIN".equalsIgnoreCase(user.getRole().toString())) {
            return PagesConstant.ADMIN_PROFILE;
        }
        return PagesConstant.PROFILE_PAGE;
    }

    private String reAuth(HttpServletRequest request) {
        request.setAttribute("authFailed", true);
        return PagesConstant.AUTHORIZATION_PAGE;
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
