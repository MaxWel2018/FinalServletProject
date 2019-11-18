package university.util;

public final class RegexTemplate {

    public static final String REGEX_FOR_NAME = "[A-Za-zА-Яа-яёЁ]{2,200}";
    public static final String REGEX_FOR_PASSWORD = "[A-Za-zA-Яа-яёЁ!_#$%^&*()-=+-]{2,32}";
    public static final String REGEX_FOR_EMAIL =
            "[a-zA-Z0-9]{1,}[@]{1}[a-z]{3,}[.]{1}+[a-z]{2,}";


    private RegexTemplate() {
    }


}
