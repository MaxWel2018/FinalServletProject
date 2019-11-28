package university.controller.command.user;

import university.controller.command.Command;
import university.domain.User;
import university.model.service.PasswordInCode;
import university.model.service.UserService;
import university.model.service.exception.EntityAlreadyExistException;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RegisterCommand implements Command {

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String firstName = request.getParameter("name");
        final String secondName = request.getParameter("secondName");
        final String password1 = (String) request.getSession().getAttribute("passwordEncode");
        final String password2 = (String) request.getSession().getAttribute("confirmPassword");
        if (!Objects.equals(password1, password2)) {
            return PagesConstant.REGISTRATION_PAGE;
        }
        final User user = mapUser(email, firstName, secondName, password1);

        try {
            userService.register(user);
        } catch (EntityAlreadyExistException e) {
            return reRegistration(request, email, firstName, secondName);
        }

        return PagesConstant.AUTHORIZATION_PAGE;
    }

    private String reRegistration(HttpServletRequest request, String email, String firstName, String secondName) {
        request.setAttribute("firstName", firstName);
        request.setAttribute("secondName", secondName);
        request.setAttribute("email", email);
        request.setAttribute("isAlreadyExist", true);
        return PagesConstant.REGISTRATION_PAGE;
    }

    private User mapUser(String email, String firstName, String secondName, String password) {
        return User.newBuilder()
                .withEmail(email)
                .withPassword(password)
                .withFirstName(firstName)
                .withSecondName(secondName)
                .build();
    }
}
