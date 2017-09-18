package validator;

/** Class of validation method for user entries. */
public class Validator {
    /**
     * Function test if the String null or empty.
     * 
     * @return 0 if true, 1 if false
     * @param string
     *            the string to be tested
     */
    public static final int validationName(final String string) {
        if (string == null || string.trim().length() == 0) {
            System.out.println("Le nom du nouvel ordinateur ne peut pas être vide");
            return 0;
        }
        return 1;
    }

}
