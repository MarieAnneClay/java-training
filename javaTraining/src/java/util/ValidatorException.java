package util;

public class ValidatorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** RuntimeException for DAO.
     * @param message error message */
    public ValidatorException(String message) {
        super(message);
    }

    /** RuntimeException for DAO configuration.
     * @param message error message
     * @param cause error cause */
    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    /** RuntimeException for DAO configuration.
     * @param cause error cause */
    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
