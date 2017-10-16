package persistence.daoUtil;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    private static HikariDataSource ds;

    /** CONSTRUCTOR.
     * @throws DAOConfigurationException a DAO exception (src/main/daoUtil) */
    private ConnectionManager() throws DAOConfigurationException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        ds = new HikariDataSource((HikariConfig) context.getBean("dataSource"));
        // context.close();
    }

    /** function which connect to the database.
     * @exception SQLException if the connection encounter an issue
     * @return a Connection */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}