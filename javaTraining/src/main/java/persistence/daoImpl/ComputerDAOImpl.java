package persistence.daoImpl;

import static persistence.daoUtil.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Computer;
import persistence.dao.ComputerDAO;
import persistence.daoUtil.ConnectionManager;
import persistence.daoUtil.DAOException;
import persistence.daoUtil.TransactionManager;

public class ComputerDAOImpl implements ComputerDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private TransactionManager transactionManager;
    private static final ComputerDAOImpl INSTANCE = new ComputerDAOImpl();
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM computer cr " + "LEFT JOIN company cy ON cr.company_id = cy.id " + "WHERE cr.name LIKE CONCAT('%', ? , '%') "
            + "OR cy.name LIKE CONCAT('%', ? , '%') ";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM computer WHERE id = ?";
    private static final String SQL_UPDATE_COMPANY_ID = "UPDATE computer SET company_id = ? WHERE company_id = ?";

    public static ComputerDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Computer findByIdComputer(long id) throws DAOException {
        Computer computer = null;

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_ID, id);
                ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                computer = mapComputer(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }

        return computer;
    }

    @Override
    public ArrayList<Computer> findComputerByNameAndCompany(String name, int numberOfComputerByPage, int currentPage, String sort, String order) throws DAOException {
        Computer computer = null;
        ArrayList<Computer> computers = new ArrayList<Computer>();
        int beginIndex = currentPage * numberOfComputerByPage;
        int endIndex = numberOfComputerByPage;

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion,
                        "SELECT * FROM computer cr LEFT JOIN company cy ON cr.company_id = cy.id " + " WHERE cr.name LIKE '%" + name + "%' " + "OR cy.name LIKE '%" + name + "%' " + "ORDER BY " + sort
                                + " " + order + "" + " LIMIT " + beginIndex + "," + endIndex + "");
                ResultSet resultSet = preparedStatement.executeQuery();) {
            LOGGER.log(Level.SEVERE, preparedStatement.toString());
            while (resultSet.next()) {
                computer = mapComputer(resultSet);
                computers.add(computer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }

        return computers;
    }

    @Override
    public int getCount(String name) {
        int count = 0;
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_COUNT, name, name);
                ResultSet resultSet = preparedStatement.executeQuery();) {
            resultSet.next();
            count = ((Number) resultSet.getObject(1)).intValue();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }
        return count;
    }

    @Override
    public void createComputer(Computer computer) throws IllegalArgumentException, DAOException {

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(),
                        ((computer.getCompanyId() == 0) ? null : computer.getCompanyId()));) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public void updateComputer(Computer computer) throws DAOException {

        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(),
                        ((computer.getCompanyId() == 0) ? null : computer.getCompanyId()), computer.getId());) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteComputer(long id) throws DAOException {
        try (Connection connexion = connectionManager.getConnection(); PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_BY_ID, id);) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /** Mapper function for computerDAO.
     * @throws SQLException if the resultSet of the query as an exception
     * @param resultSet result of the query select
     * @return return the computer mapped */
    public static Computer mapComputer(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getLong("id"));
        computer.setName(resultSet.getString("name"));
        computer.setIntroduced(resultSet.getDate("introduced") == null ? null : resultSet.getDate("introduced").toLocalDate());
        computer.setDiscontinued(resultSet.getDate("discontinued") == null ? null : resultSet.getDate("discontinued").toLocalDate());
        computer.setCompanyId(resultSet.getLong("company_id"));
        return computer;
    }

    @Override
    public void updateCompanyId(long id) throws DAOException {
        Connection connexion = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_COMPANY_ID, null, id);) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DAOException(e);
        }
    }
}
