package university.util;

public final class RegexTemplate {

    public static final String REGEX_FOR_NAME = "[A-Za-zА-Яа-яёЁ]{2,200}";
    public static final String REGEX_FOR_PASSWORD = ""; //TODO сделать регу для пароля
    public static final String REGEX_FOR_EMAIL =
            "[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}";


    private RegexTemplate() {
    }


}
