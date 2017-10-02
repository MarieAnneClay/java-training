package persistence.daoUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public TransactionManager() {
        super();
    }

    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }

    public static Connection getConnection() {
        return threadLocal.get();
    }

    public static void setAutoCommit(boolean autoCommit) throws SQLException {
        threadLocal.get().setAutoCommit(autoCommit);
    }

    public static void commit() throws SQLException {
        threadLocal.get().commit();
    }

    public static void rollback() throws SQLException {
        threadLocal.get().commit();
    }

    public static void remove() throws SQLException {
        threadLocal.remove();
    }

}
