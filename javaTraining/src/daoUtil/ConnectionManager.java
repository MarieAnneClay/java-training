package daoUtil;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dao.CompanyDAO;
import dao.ComputerDAO;
import daoImpl.CompanyDAOImpl;
import daoImpl.ComputerDAOImpl;

public class ConnectionManager {
	
	private static final String FICHIER_PROPERTIES = "dao/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

    private String url;
    private String username;
    private String password;
    private final static ConnectionManager instance = new ConnectionManager();

  	/** Point d'accès pour l'instance unique du singleton */
  	public static ConnectionManager getInstance () {
  		return instance;
  	}


    ConnectionManager( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private ConnectionManager () throws DAOConfigurationException{

    	String url = "jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "ebiz";

        /*
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( FileNotFoundException e ) {
        	throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        } catch ( IOException e ) {
        	System.out.println("Impossible de charger le fichier properties " + FICHIER_PROPERTIES );
        }*/

        try {
            Class.forName(driver);
        } catch ( ClassNotFoundException e ) {
        	throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }
        
        this.url = url;
		this.username = username;
		this.password = password;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    public ComputerDAO getComputerDao() {
        return new ComputerDAOImpl(this);
    }
    
    public CompanyDAO getCompanyDao() {
        return new CompanyDAOImpl(this);
    }
}