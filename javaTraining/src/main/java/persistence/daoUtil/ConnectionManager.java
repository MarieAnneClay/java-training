package persistence.daoUtil;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    HikariDataSource ds;
    private static final ConnectionManager INSTANCE = new ConnectionManager();

    /** Point d'accès pour l'instance unique du singleton.
     * @return a unique connexion to the database */
    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    /** CONSTRUCTOR.
     * @throws DAOConfigurationException a DAO exception (src/main/daoUtil) */
    private ConnectionManager() throws DAOConfigurationException {
        // HikariConfig cfg = new HikariConfig(configFile);
        HikariConfig cfg = new HikariConfig();
        // cfg.setPoolName(conf.getString("poolName"));
        cfg.setMaximumPoolSize(10);
        cfg.setMinimumIdle(2);
        cfg.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&useSSL=false");
        cfg.setUsername("admincdb");
        cfg.setPassword("qwerty1234");

        cfg.addDataSourceProperty("cachePrepStmts", true);
        cfg.addDataSourceProperty("prepStmtCacheSize", 250);
        cfg.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        // cfg.addDataSourceProperty("useServerPrepStmts",
        // conf.getBoolean("useServerPrepStmts"));
        ds = new HikariDataSource(cfg);
    }

    /** function which connect to the database.
     * @exception SQLException if the connection encounter an issue
     * @return a Connection */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}