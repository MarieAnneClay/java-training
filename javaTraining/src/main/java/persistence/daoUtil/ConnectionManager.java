package persistence.daoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static final String CONFIG_FILE = "src/main/resources/db.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USER = "username";
    private static final String PROPERTY_PASS = "password";

    private String url;
    private String username;
    private String password;
    private String driver;
    private static final ConnectionManager INSTANCE = new ConnectionManager();

    /** Point d'accès pour l'instance unique du singleton.
     * @return a unique connexion to the database */
    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    /** CONSTRUCTOR.
     * @param url url of the database
     * @param username for the connexion of the database
     * @param password to connect to the database */
    ConnectionManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /** CONSTRUCTOR.
     * @throws DAOConfigurationException a DAO exception (src/main/daoUtil) */
    private ConnectionManager() throws DAOConfigurationException {

        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertyFile = classLoader.getResourceAsStream(CONFIG_FILE);

        if (propertyFile == null) {
            throw new DAOConfigurationException("The properties file \"" + CONFIG_FILE + "\" does not exist.");
        }
        try {
            properties.load(propertyFile);
            this.url = properties.getProperty(PROPERTY_URL);
            this.driver = properties.getProperty(PROPERTY_DRIVER);
            this.username = properties.getProperty(PROPERTY_USER);
            this.password = properties.getProperty(PROPERTY_PASS);
            Class.forName(this.driver);
        } catch (IOException e) {
            throw new DAOConfigurationException("Unable to load file \"" + CONFIG_FILE + "\" : ", e);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Can't find driver in classpath : ", e);
        }

        this.url = url;
        this.username = username;
        this.password = password;
    }

    /** function which connect to the database.
     * @exception SQLException if the connection encounter an issue
     * @return a Connection */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}