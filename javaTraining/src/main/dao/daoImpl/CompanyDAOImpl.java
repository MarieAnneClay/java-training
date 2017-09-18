package main.dao.daoImpl;

import static main.dao.daoUtil.DAOUtilitaire.fermeturesSilencieuses;
import static main.dao.daoUtil.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import main.dao.dao.CompanyDAO;
import main.dao.daoUtil.ConnectionManager;
import main.dao.daoUtil.DAOException;
import main.model.Company;

public class CompanyDAOImpl implements CompanyDAO {
    private ConnectionManager daoFactory;
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";

    /** CONSTRUCTOR.
     * @param connexionManager the unique singleton connexion to the database
     */
    public CompanyDAOImpl(ConnectionManager connexionManager) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Company> findAllCompanies() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Company company = null;
        ArrayList<Company> companies = new ArrayList<Company>();

        try {
            connexion = (Connection) daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                company = map(resultSet);
                companies.add(company);
            }
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION SELECT COMPANY");
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return companies;
    }

    @Override
    public Company findByIdCompany(long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Company company = null;

        try {
            connexion = (Connection) daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_ID, false, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                company = map(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION SELECT COMPANY");
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return company;
    }

    /** Mapper function for companyDAO.
     * @throws SQLException if the resultSet of the query as an exception
     * @param resultSet result of the query select
     * @return return the company mapped
     */
    private static Company map(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        return company;
    }
}