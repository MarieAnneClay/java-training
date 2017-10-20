package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/** Class of validation method for user entries. */
public class Validator {
    /** Function test if the String null or empty.
     * @param name the string to be tested
     * @throws Exception */
    public static String validationName(final String name) {
        if (name == null || name.trim().length() == 0) {
            return "The name of the computer can't be empty.";
        }
        if (name.matches("^[0-9a-zA-Zàâéèëêïîôùüç -]{1,60}$") == false) {
            return "The name of the computer is invalid. It can contains number, uppercase, space and accent with maximum 60 characters.";
        }
        return null;
    }

    /** Function test if the date is valid.
     * @param date string
     * @throws Exception */
    public static String validationDate(String date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
        if (date != null && date.trim().length() != 0) {
            try {
                fmt.parse(date);
            } catch (ParseException e) {
                return "The format of the date is invalid.";
            }
        }
        return null;
    }

    /** Function test if the introduced date enter is before the discontinued date.
     * @param introducedDate LocalDate
     * @param discontinuedDate LocalDate
     * @throws Exception */
    public static String validationIntroducedBeforeDiscontinued(LocalDate introducedDate, LocalDate discontinuedDate) throws ValidatorException {
        if (introducedDate != null && discontinuedDate != null) {
            if (introducedDate.isAfter(discontinuedDate)) {
                return "Introduced date later then discontinued date.";
            }
        }
        return null;
    }

    public static void validationComputer(String name, LocalDate introduced, LocalDate discontinued) throws ValidatorException {
        Validator.validationName(name);
        Validator.validationIntroducedBeforeDiscontinued(introduced, discontinued);

    }

}
