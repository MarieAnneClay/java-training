package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/** Class of validation method for user entries. */
public class Validator {
    /**
     * Function test if the String null or empty.
     * 
     * @return 0 if true, 1 if false
     * @param string
     *            the string to be tested
     */
    public static void validationName(final String name) throws Exception {
        if (name == null || name.trim().length() == 0) {
            throw new Exception("Le nom du nouveau membre ne peut pas être vide");
        }
    }

    public static void validationDate(String birthdate) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
        if (birthdate != null && birthdate.trim().length() != 0) {
            try {
                fmt.parse(birthdate);
            } catch (ParseException e) {
                throw new Exception("Le format de la date de naissance n'est pas valide.");
            }
        } else {
            throw new Exception("La date de naissance ne peut pas être vide");
        }
    }

}
