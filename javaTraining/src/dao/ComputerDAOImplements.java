package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Computer;

public class ComputerDAOImplements implements ComputerDAO{
	
	private DAOFactory daoFactory;
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM computer WHERE id = ?";
	
	public ComputerDAOImplements(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
    @Override
    public void creer(Computer Computer) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, Computer.getName(), Computer.getIntroduced(), Computer.getDiscontinued(), ( (Computer.getCompanyId() == 0)? null : Computer.getCompanyId() ) );
            int statut = preparedStatement.executeUpdate();
            if( statut == 0 ){
            	System.out.println("==0");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if( valeursAutoGenerees.next() ){
                Computer.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
            	System.out.println("ELSE  INSERT COMPUTER");
            }
        } catch ( SQLException e ) {
        	System.out.println("SQL EXCEPTION INSERT COMPUTER");
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    @Override
    public void modifier(Computer computer) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), ( (computer.getCompanyId() == 0)? null : computer.getCompanyId() ), computer.getId() );
            int statut = preparedStatement.executeUpdate();
            if( statut == 0 ){
            	System.out.println(SQL_UPDATE+computer.getName()+ computer.getIntroduced()+ computer.getDiscontinued()+ ( (computer.getCompanyId() == 0)? null : computer.getCompanyId() )+ computer.getId() );
            }
        } catch ( SQLException e ) {
        	System.out.println("SQL EXCEPTION UPDATE COMPUTER");
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    public LinkedList<Computer> trouver(String sql, Object... objets){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Computer computer = null;
        LinkedList<Computer> computers = new LinkedList<Computer>();

        try {
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {
            	computer = map(resultSet);
            	computers.add(computer);
            }
        } catch ( SQLException e ) {
        	System.out.println("SQL EXCEPTION SELECT COMPUTER");
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return computers;
    }
	
    @Override
    public void supprimer(Computer computer) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
        	connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, computer.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
            	System.out.println("Échec de la suppression de la commande, aucune ligne supprimée de la table.");
            } else {
            	computer.setId(0);
            }
        } catch ( SQLException e ) {
        	System.out.println("SQL EXCEPTION DELETE COMPUTER");
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

	private static Computer map(ResultSet resultSet) throws SQLException {
		Computer computer = new Computer();
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));
		computer.setIntroduced(resultSet.getTimestamp("introduced"));
		computer.setDiscontinued(resultSet.getTimestamp("discontinued"));
		computer.setCompanyId(resultSet.getLong("company_id"));
	    return computer;
	}
}
