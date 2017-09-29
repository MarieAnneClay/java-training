package persistence.daoImpl;

import static persistence.daoUtil.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Company;
import persistence.dao.CompanyDAO;
import persistence.daoUtil.ConnectionManager;
import persistence.daoUtil.DAOException;
import persistence.daoUtil.TransactionManager;

public class CompanyDAOImpl implements CompanyDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private static final CompanyDAOImpl INSTANCE = new CompanyDAOImpl();
    private TransactionManager transactionManager;
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO company (name) VALUES (?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM company WHERE id = ?";

    public static CompanyDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ArrayList<Company> findAllCompanies() {
        Company company = null;
        ArrayList<Company> companies = new ArrayList<Company>();

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL);
                ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                company = map(resultSet);
                companies.add(company);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }

        return companies;
    }

    @Override
    public Company findByIdCompany(long id) throws DAOException {
        Company company = null;

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_ID, id);
                ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                company = map(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }

        return company;
    }

    /** Mapper function for companyDAO.
     * @throws SQLException if the resultSet of the query as an exception
     * @param resultSet result of the query select
     * @return return the company mapped */
    private static Company map(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        return company;
    }

    @Override
    public void createCompany(Company company) throws IllegalArgumentException, DAOException {

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, company.getName());
                ResultSet valeursAutoGenerees = preparedStatement.getGeneratedKeys();) {

            if (valeursAutoGenerees.next()) {
                company.setId(valeursAutoGenerees.getLong(1));
            } else {
                LOGGER.log(Level.SEVERE, "ELSE  INSERT COMPANY");
                System.out.println("ELSE  INSERT COMPANY");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteCompany(long id) throws DAOException {
        Connection connexion = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_BY_ID, id);) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }
    }
}