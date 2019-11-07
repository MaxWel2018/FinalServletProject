package university.command.user;

import university.command.Command;
import university.model.service.contract.UserService;
import university.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email =  request.getParameter("email");
        final String firstName = (String) request.getParameter("name");
        final String secondName = request.getParameter("secondName");

        final String password1 = (String) request.getAttribute("passwordEncode");
        final String password2 = (String) request.getAttribute("confirmPassword");
        if (!Objects.equals(password1, password2)) {
            return PagesConstant.REGISTRATION_PAGE;
        }
        final User user = User.newBuilder()
                .withEmail(email)
                .withPassword(password1)
                .withFirstName(firstName)
                .withSecondName(secondName)
                .build();

        userService.register(user);

        return PagesConstant.HOME_PAGE;
    }
}