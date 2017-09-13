package dao;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
	
	private static final String FICHIER_PROPERTIES = "/dao/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

    private String url;
    private String username;
    private String password;


    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {

    	String url = "jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String nomUtilisateur = "root";
        String motDePasse = "ebiz";

/*
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        BufferedInputStream fichierProperties = (BufferedInputStream) classLoader.getResourceAsStream( FICHIER_PROPERTIES );


        if ( fichierProperties == null ) {

            System.out.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );

        }


        try {

            properties.load( fichierProperties );

            url = properties.getProperty( PROPERTY_URL );

            driver = properties.getProperty( PROPERTY_DRIVER );

            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );

            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );

        } catch ( FileNotFoundException e ) {
        	System.out.println("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );

        } catch ( IOException e ) {
        	System.out.println("Impossible de charger le fichier properties " + FICHIER_PROPERTIES );

        }


        try {

            Class.forName( driver );

        } catch ( ClassNotFoundException e ) {
        	System.out.println("Le driver est introuvable dans le classpath.");

        }
*/
        try {
            Class.forName(driver);
        } catch ( ClassNotFoundException e ) {
        	
        }
        
        DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
        return instance;

    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    public ComputerDAO getComputerDao() {
        return new ComputerDAOImplements(this);
    }
    
    public CompanyDAO getCompanyDao() {
        return new CompanyDAOImplements(this);
    }
}