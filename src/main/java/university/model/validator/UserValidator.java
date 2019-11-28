package university.model.validator;

import university.domain.User;
import university.model.service.exception.InvalidDateException;
import university.model.service.exception.InvalidEmailException;
import university.model.service.exception.InvalidPasswordException;

import java.util.Optional;
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
    public void validate(User entity) {
        emailValidate(entity);
        passwordValidate(entity);
        validateFirstName(entity);
        validateSecondName(entity);
    }

    private void validateSecondName(User entity) {
        Optional.ofNullable(entity)
                .map(User::getSecondName)
                .filter(x -> REGEX_FOR_NAME_PATERN.matcher(x).matches())
                .orElseThrow(() -> new InvalidDateException("Second name invalidate"));
    }

    private void validateFirstName(User entity) {
        Optional.ofNullable(entity)
                .map(User::getFirstName)
                .filter(x -> REGEX_FOR_NAME_PATERN.matcher(x).matches())
                .orElseThrow(() -> new InvalidDateException("First name  invalidate"));
    }

    private void passwordValidate(User entity) {
        Optional.ofNullable(entity)
                .map(User::getPassword)
                .filter((x -> PASSWORD_PATTERN.matcher(x).matches()))
                .orElseThrow(() -> new InvalidPasswordException("Password  invalidate"));
    }

    private void emailValidate(User entity) {
        Optional.ofNullable(entity)
                .map(User::getEmail)
                .filter(x -> EMAIL_PATTERN.matcher(x).matches())
                .orElseThrow(() -> new InvalidEmailException("Email  invalidate"));
    }
}


