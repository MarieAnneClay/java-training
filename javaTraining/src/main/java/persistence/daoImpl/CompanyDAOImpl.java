package persistence.daoImpl;

import static persistence.daoUtil.DAOUtilitaire.fermetureSilencieuse;
import static persistence.daoUtil.DAOUtilitaire.fermeturesSilencieuses;
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

public class CompanyDAOImpl implements CompanyDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO company (name) VALUES (?)";
    private static final String SQL_UPDATE_COMPANY_ID = "UPDATE computer SET company_id = ? WHERE company_id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM company WHERE id = ?";

    @Override
    public ArrayList<Company> findAllCompanies() {
        Company company = null;
        ArrayList<Company> companies = new ArrayList<Company>();

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
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
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_ID, false, id);
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
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, company.getName());
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
        Connection connexion = null;
        PreparedStatement preparedStatementUpdate = null;
        PreparedStatement preparedStatementDelete = null;

        try {
            connexion = connectionManager.getConnection();
            connexion.setAutoCommit(false);

            preparedStatementUpdate = initialisationRequetePreparee(connexion, SQL_UPDATE_COMPANY_ID, true, null, id);
            preparedStatementDelete = initialisationRequetePreparee(connexion, SQL_DELETE_BY_ID, true, id);

            preparedStatementUpdate.executeUpdate();
            preparedStatementDelete.executeUpdate();

            connexion.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (connexion != null) {
                try {
                    connexion.rollback();
                } catch (SQLException excep) {
                    LOGGER.log(Level.SEVERE, excep.getMessage(), excep);
                    throw new DAOException(excep);
                }
            }

        } finally {
            if (preparedStatementUpdate != null) {
                fermeturesSilencieuses(preparedStatementUpdate, connexion);
            }
            if (preparedStatementDelete != null) {
                fermeturesSilencieuses(preparedStatementDelete, connexion);
            }
            if (connexion != null) {
                fermetureSilencieuse(connexion);
            }
        }
    }
}