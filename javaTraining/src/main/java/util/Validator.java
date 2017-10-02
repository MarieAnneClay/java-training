package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/** Class of validation method for user entries. */
public class Validator {
    /** Function test if the String null or empty.
     * @param name the string to be tested
     * @throws Exception */
    public static void validationName(final String name) throws ValidatorException {
        if (name == null || name.trim().length() == 0) {
            throw new ValidatorException("The name of the computer can't be empty.");
        }
        if (!name.matches("^[0-9a-zA-Zàâéèëêïîôùüç -_]{1,60}$")) {
            throw new ValidatorException("The name of the computer is invalid. It can contains number, uppercase, space and accent with maximum 60 characters.");
        }
    }

    /** Function test if the date is valid.
     * @param date string
     * @throws Exception */
    public static void validationDate(String date) throws ValidatorException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
        if (date != null && date.trim().length() != 0) {
            try {
                fmt.parse(date);
            } catch (ParseException e) {
                throw new ValidatorException("The format of the date is invalid.");
            }
        }
    }

    /** Function test if the introduced date enter is before the discontinued date.
     * @param introducedDate LocalDate
     * @param discontinuedDate LocalDate
     * @throws Exception */
    public static void validationIntroducedBeforeDiscontinued(LocalDate introducedDate, LocalDate discontinuedDate) throws ValidatorException {
        if (introducedDate != null && discontinuedDate != null) {
            if (introducedDate.isAfter(discontinuedDate)) {
                throw new ValidatorException("Introduced date later then discontinued date.");
            }
        }
    }

    public static void validationComputer(String name, LocalDate introduced, LocalDate discontinued) throws ValidatorException {
        Validator.validationName(name);
        Validator.validationIntroducedBeforeDiscontinued(introduced, discontinued);

    }

}
