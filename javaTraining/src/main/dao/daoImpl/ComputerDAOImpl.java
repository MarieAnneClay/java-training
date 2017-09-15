package main.dao.daoImpl;

import static main.dao.daoUtil.DAOUtilitaire.fermeturesSilencieuses;
import static main.dao.daoUtil.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import main.dao.dao.ComputerDAO;
import main.dao.daoUtil.ConnectionManager;
import main.dao.daoUtil.DAOException;
import main.model.Computer;

public class ComputerDAOImpl implements ComputerDAO {

	private ConnectionManager connexionManager;
	private static final String SQL_SELECT_ALL = "SELECT * FROM computer";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM computer WHERE id = ?";

	public ComputerDAOImpl(ConnectionManager connexionManager) {
		this.connexionManager = connexionManager;
	}

	@Override
	public ArrayList<Computer> findAllComputers() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		ArrayList<Computer> computers = new ArrayList<Computer>();

		try {
			connexion = (Connection) connexionManager.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				computer = mapComputer(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return computers;
	}

	@Override
	public Computer findByIdComputer(long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;

		try {
			connexion = (Connection) connexionManager.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				computer = mapComputer(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return computer;
	}

	@Override
	public void createComputer(Computer Computer) throws IllegalArgumentException, DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = (Connection) connexionManager.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, Computer.getName(),
					Computer.getIntroduced(), Computer.getDiscontinued(),
					((Computer.getCompanyId() == 0) ? null : Computer.getCompanyId()));
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				System.out.println("==0");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				Computer.setId(valeursAutoGenerees.getLong(1));
			} else {
				System.out.println("ELSE  INSERT COMPUTER");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	@Override
	public void updateComputer(Computer computer) throws IllegalArgumentException, DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = (Connection) connexionManager.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, true, computer.getName(),
					computer.getIntroduced(), computer.getDiscontinued(),
					((computer.getCompanyId() == 0) ? null : computer.getCompanyId()), computer.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				System.out
						.println(SQL_UPDATE + computer.getName() + computer.getIntroduced() + computer.getDiscontinued()
								+ ((computer.getCompanyId() == 0) ? null : computer.getCompanyId()) + computer.getId());
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	@Override
	public void deleteComputer(long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = (Connection) connexionManager.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_BY_ID, true, id);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				System.out.println("Échec de la suppression de la commande, aucune ligne supprimée de la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
	}

	public static Computer mapComputer(ResultSet resultSet) throws SQLException {
		Computer computer = new Computer();
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));
		computer.setIntroduced(resultSet.getTimestamp("introduced"));
		computer.setDiscontinued(resultSet.getTimestamp("discontinued"));
		computer.setCompanyId(resultSet.getLong("company_id"));
		return computer;
	}
}
