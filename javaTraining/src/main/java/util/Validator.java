package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/** Class of validation method for user entries. */
public class Validator {
    /** Function test if the String null or empty.
     * @param name the string to be tested
     * @throws Exception */
    public static void validationName(final String name) throws Exception {
        if (name == null || name.trim().length() == 0) {
            throw new Exception("Le nom du nouveau membre ne peut pas être vide");
        }
    }

    /** Function test if the date is valid.
     * @param date string
     * @throws Exception */
    public static void validationDate(String date) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
        if (date != null && date.trim().length() != 0) {
            try {
                fmt.parse(date);
            } catch (ParseException e) {
                throw new Exception("Le format de la date n'est pas valide.");
            }
        }
    }

}
