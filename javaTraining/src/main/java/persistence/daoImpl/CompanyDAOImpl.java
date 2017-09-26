package persistence.daoImpl;

import static persistence.daoUtil.DAOUtilitaire.fermetureSilencieuse;
import static persistence.daoUtil.DAOUtilitaire.fermeturesSilencieuses;
import static persistence.daoUtil.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Company;
import persistence.dao.CompanyDAO;
import persistence.daoUtil.ConnectionManager;
import persistence.daoUtil.DAOException;

public class CompanyDAOImpl implements CompanyDAO {
    private ConnectionManager connexionManager;
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO company (name) VALUES (?)";
    private static final String SQL_UPDATE_COMPANY_ID = "UPDATE computer SET company_id = ? WHERE company_id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM company WHERE id = ?";

    /** CONSTRUCTOR.
     * @param connexionManager the unique singleton connexion to the database */
    public CompanyDAOImpl(ConnectionManager connexionManager) {
        this.connexionManager = connexionManager;
    }

    @Override
    public ArrayList<Company> findAllCompanies() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Company company = null;
        ArrayList<Company> companies = new ArrayList<Company>();

        try {
            connexion = (Connection) connexionManager.getConnection();

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
            connexion = (Connection) connexionManager.getConnection();

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
     * @return return the company mapped */
    private static Company map(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        return company;
    }

    @Override
    public void createCompany(Company company) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = (Connection) connexionManager.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, company.getName());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                System.out.println("==0");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                company.setId(valeursAutoGenerees.getLong(1));
            } else {
                System.out.println("ELSE  INSERT COMPANY");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public void deleteCompany(long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatementUpdate = null;
        PreparedStatement preparedStatementDelete = null;

        try {
            connexion = (Connection) connexionManager.getConnection();
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