package main.dao.daoUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.dao.dao.CompanyDAO;
import main.dao.dao.ComputerDAO;
import main.dao.daoImpl.CompanyDAOImpl;
import main.dao.daoImpl.ComputerDAOImpl;

public class ConnectionManager {

    private String url;
    private String username;
    private String password;
    private static final ConnectionManager instance = new ConnectionManager();

    /** Point d'accès pour l'instance unique du singleton.
     * @return a unique connexion to the database */
    public static ConnectionManager getInstance() {
        return instance;
    }

    /** CONSTRUCTOR.
     * @param url url of the database
     * @param username for the connexion of the database
     * @param password to connect to the database*/
    ConnectionManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /** CONSTRUCTOR.
     * @throws DAOConfigurationException a DAO exception (src/main/daoUtil)*/
    private ConnectionManager() throws DAOConfigurationException {

        String url = "jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "ebiz";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
        }

        this.url = url;
        this.username = username;
        this.password = password;
    }

    /** funcion wich connect to the database.
     * @exception SQLException if the connection encounter an issue
     * @return a Connection*/
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public ComputerDAO getComputerDao() {
        return new ComputerDAOImpl(this);
    }

    public CompanyDAO getCompanyDao() {
        return new CompanyDAOImpl(this);
    }
}