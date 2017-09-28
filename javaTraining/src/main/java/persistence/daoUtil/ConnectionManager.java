package persistence.daoUtil;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    private static HikariDataSource ds;
    private static final String FILE_CONFIG = "/db.properties";
    private static final ConnectionManager INSTANCE = new ConnectionManager();

    /** Point d'accès pour l'instance unique du singleton.
     * @return a unique connexion to the database */
    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    /** CONSTRUCTOR.
     * @throws DAOConfigurationException a DAO exception (src/main/daoUtil) */
    private ConnectionManager() throws DAOConfigurationException {
        HikariConfig cfg = new HikariConfig(FILE_CONFIG);
        ds = new HikariDataSource(cfg);
    }

    /** function which connect to the database.
     * @exception SQLException if the connection encounter an issue
     * @return a Connection */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}