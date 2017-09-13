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
	
	private DAOFactory daoFactory;
    private static final String SQL_INSERT = "INSERT INTO Company (name) VALUES (?)";
	
	public CompanyDAOImplements(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
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
        	System.out.println("SQL EXCEPTION SELECT COMPANY");
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