package university.model.validator;

import university.domain.User;
import university.model.service.exception.InvalidDateException;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;


public class UserValidator implements Validator<User> {
    private static final String REGEX_FOR_NAME = "[A-Za-zА-Яа-яёЁ]{2,200}";
    private static final String REGEX_FOR_PASSWORD = "[A-Za-zA-Яа-яёЁ!_#$%^&*()-=+-]{2,32}";
    private static final String REGEX_FOR_EMAIL =
            "[a-zA-Z0-9]{1,}[@]{1}[a-z]{3,}[.]{1}+[a-z]{2,}";

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(REGEX_FOR_EMAIL);
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(REGEX_FOR_PASSWORD);
    private static final Pattern REGEX_FOR_NAME_PATERN =
            Pattern.compile(REGEX_FOR_NAME);

    @Override
    public void validate(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        validateByParam(User::getPassword, PASSWORD_PATTERN, "Password invalidate", user);
        validateByParam(User::getSecondName, REGEX_FOR_NAME_PATERN, "Second name invalidate", user);
        validateByParam(User::getFirstName, REGEX_FOR_NAME_PATERN, "First name invalidate", user);
        validateByParam(User::getEmail, EMAIL_PATTERN, "Email invalidate", user);
    }

    private void validateByParam(Function<User, String> param, Pattern pattern, String errorMessage, User user) {
        Optional.ofNullable(param.apply(user))
                .filter(x -> pattern.matcher(x).matches())
                .orElseThrow(() -> new InvalidDateException(errorMessage));
    }
}
