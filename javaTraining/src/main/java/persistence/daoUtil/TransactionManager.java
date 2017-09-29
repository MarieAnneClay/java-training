package persistence.daoUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public TransactionManager() {
        super();
    }

    public void setConnection(Connection connection) {
        threadLocal.set(connection);
    }

    public Connection getConnection() {
        return threadLocal.get();
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        threadLocal.get().setAutoCommit(autoCommit);
    }

    public void commit() throws SQLException {
        threadLocal.get().commit();
    }

    public void rollback() throws SQLException {
        threadLocal.get().commit();
    }

    public void remove() throws SQLException {
        threadLocal.remove();
    }

}
