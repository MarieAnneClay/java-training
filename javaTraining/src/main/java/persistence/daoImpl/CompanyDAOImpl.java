package persistence.daoImpl;

import static persistence.daoUtil.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

import model.Company;
import persistence.dao.CompanyDAO;
import persistence.daoUtil.ConnectionManager;
import persistence.daoUtil.DAOException;

public class CompanyDAOImpl implements CompanyDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private ConnectionManager connectionManager;
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";

    public ConnectionManager getConnection() {
        return connectionManager;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
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

        String query = "INSERT INTO company (name) VALUES '" + company.getName() + "')";
        jdbcTemplate.update(query);

    }

    @Override
    public void deleteCompany(long id) throws DAOException {
        String query = "DELETE FROM company WHERE id = '" + id + "' ";
        jdbcTemplate.update(query);
    }
}