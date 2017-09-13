package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Company;

public class CompanyDAOImplements implements CompanyDAO{
	
	private DAOFactory          daoFactory;
    private static final String SQL_INSERT           = "INSERT INTO Company (name) VALUES (?)";
	
	public CompanyDAOImplements(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
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

    public LinkedList<Company> trouver(String sql, Object... objets){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Company company = null;
        LinkedList<Company> companies = new LinkedList<Company>();

        try {
            connexion = (Connection) daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            
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

	private static Company map(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
	    return company;
	}
}