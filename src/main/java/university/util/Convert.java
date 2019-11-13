package university.util;

import java.sql.Date;
import java.time.LocalDate;

import static java.sql.Date.valueOf;

public final class Convert {
    private Convert() {

    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return new Date(date.getTime()).toLocalDate();
    }

    public static Date convertLocaleDateToDate(LocalDate localDate) {
        return valueOf(localDate);
    }

}
