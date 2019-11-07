package university.model.service.validator;

import university.model.service.exception.InvalidDateException;
import university.model.service.exception.InvalidEmailException;
import university.model.service.exception.InvalidPasswordException;
import university.domain.User;
import university.utility.RegexTemplate;

import java.util.Optional;
import java.util.regex.Pattern;


public class UserValidator implements Validator<User> {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(RegexTemplate.REGEX_FOR_EMAIL);
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(RegexTemplate.REGEX_FOR_PASSWORD);
    private static final Pattern REGEX_FOR_NAME =
            Pattern.compile(RegexTemplate.REGEX_FOR_NAME);

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
                .filter(x -> REGEX_FOR_NAME.matcher(x).matches())
                .orElseThrow(() -> new InvalidDateException("Second name invalidate"));
    }

    private void validateFirstName(User entity) {
        Optional.ofNullable(entity)
                .map(User::getFirstName)
                .filter(x -> REGEX_FOR_NAME.matcher(x).matches())
                .orElseThrow(() -> new InvalidDateException("First name  invalidate"));
    }

    private void passwordValidate(User entity) {
        Optional.ofNullable(entity)
                .map(User::getPassword)
//                .filter((x -> PASSWORD_PATTERN.matcher(x).matches()))
                .orElseThrow(() -> new InvalidPasswordException("Password  invalidate"));
    }

    private void emailValidate(User entity) {
        Optional.ofNullable(entity)
                .map(User::getEmail)
                .filter(x -> EMAIL_PATTERN.matcher(x).matches())
                .orElseThrow(()->new InvalidEmailException("Email  invalidate"));
    }
}


