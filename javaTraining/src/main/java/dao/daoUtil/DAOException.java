package dao.daoUtil;

public class DAOException extends RuntimeException {

    /**
     * RuntimeException for DAO.
     * 
     * @param message
     *            error message
     */
    public DAOException(String message) {
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
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * RuntimeException for DAO configuration.
     * 
     * @param cause
     *            error cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
