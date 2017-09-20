package persistence.daoUtil;

public class DAOConfigurationException extends RuntimeException {

    /**
     * RuntimeException for DAO configuration.
     * 
     * @param message
     *            error message
     */
    public DAOConfigurationException(String message) {
        super(message);
    }

    /**
     * RuntimeException for DAO configuration.
     * 
     * @param message
     *            error message
     * @param cause
     *            error cause
     */
    public DAOConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * RuntimeException for DAO configuration.
     * 
     * @param cause
     *            error cause
     */
    public DAOConfigurationException(Throwable cause) {
        super(cause);
    }
}
