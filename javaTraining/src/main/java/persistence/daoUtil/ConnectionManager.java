package persistence.daoUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    // private static final String FICHIER_PROPERTIES = "/db.properties";
    // private static final String PROPERTY_URL = "url";
    // private static final String PROPERTY_DRIVER = "driver";
    // private static final String PROPERTY_USER = "user";
    // private static final String PROPERTY_PASSWORD = "password";

    private String url;
    private String username;
    private String password;
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
        new Properties();

        String url = "jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String username = "admincdb";
        String password = "qwerty1234";

        // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //
        // InputStream fichierProperties =
        // classLoader.getResourceAsStream(FICHIER_PROPERTIES);
        //
        // if (fichierProperties == null) {
        // throw new DAOConfigurationException("Le fichier properties " +
        // FICHIER_PROPERTIES + " est introuvable.");
        // }
        //
        // try {
        // properties.load(fichierProperties);
        // url = properties.getProperty(PROPERTY_URL);
        // driver = properties.getProperty(PROPERTY_DRIVER);
        // username = properties.getProperty(PROPERTY_USER);
        // password = properties.getProperty(PROPERTY_PASSWORD);
        // } catch (IOException e) {
        // throw new DAOConfigurationException("Impossible de charger le fichier
        // properties " + FICHIER_PROPERTIES, e);
        // }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
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