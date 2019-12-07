package university.model.service;

import java.util.Base64;

public class PasswordInCode {

    public static String passwordEncoded(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());

    }
}
