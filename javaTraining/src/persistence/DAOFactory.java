package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private String url;
    private String username;
    private String password;


    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */

    public static DAOFactory getInstance() {

        Properties properties = new Properties();
        String url = "jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&amp;useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String nomUtilisateur = "root";
        String motDePasse = "ebiz";

        try {
            Class.forName(driver);
        } catch ( ClassNotFoundException e ) {
        	
        }
        DAOFactory instance = new DAOFactory(url, nomUtilisateur, motDePasse);
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    public ComputerDAO getUtilisateurDao() {
        return new ComputerDAOImplements(this);
    }
}