package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Company;

import static persistence.DAOUtilitaire.fermeturesSilencieuses;
import static persistence.DAOUtilitaire.initialisationRequetePreparee;

public class CompanyDAOImplements implements CompanyDAO{
	
	private DAOFactory          daoFactory;
    private static final String SQL_INSERT           = "INSERT INTO Company (name) VALUES (?)";
	
	public CompanyDAOImplements(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
	/* Implémentation de la méthode définie dans l'interface CompanyDao */
    @Override
    public void creer(Company Company) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, Company.getName());
            int statut = preparedStatement.executeUpdate();
            if( statut == 0 ){
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if( valeursAutoGenerees.next() ){
                Company.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
            }
        } catch ( SQLException e ) {
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner un Company depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramètres
     * les objets passés en argument.
     */
    public Vector<Company> trouver(String sql, Object... objets){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Company company = null;
        Vector<Company> companies = new Vector<Company>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            while ( resultSet.next() ) {
            	company = map(resultSet);
            	companies.add(company);
            }
        } catch ( SQLException e ) {
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return companies;
    }
	
	
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des Companys (un
	 * ResultSet) et un bean Company.
	 */
	private static Company map(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
	    return company;
	}
}