package university.model.service;

import java.util.Base64;

public class PasswordInCode {

    public static String passwordEncoded(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());

    }

    public static String passwordDecoded(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

}
