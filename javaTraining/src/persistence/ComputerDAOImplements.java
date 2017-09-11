package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Computer;

import static persistence.DAOUtilitaire.fermeturesSilencieuses;
import static persistence.DAOUtilitaire.initialisationRequetePreparee;

public class ComputerDAOImplements implements ComputerDAO{
	
	private DAOFactory daoFactory;
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	
	public ComputerDAOImplements(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
	/* Implémentation de la méthode définie dans l'interface ComputerDao */
    @Override
    public void creer(Computer Computer) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, Computer.getName(), Computer.getIntroduced(), Computer.getDiscontinued(), Computer.getCompanyId());
            int statut = preparedStatement.executeUpdate();
            if( statut == 0 ){
            	System.out.println("==0");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if( valeursAutoGenerees.next() ){
                Computer.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
            	System.out.println("ELSE");
            }
        } catch ( SQLException e ) {
        	System.out.println("SQL EXCEPTIO?");
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner un Computer depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramètres
     * les objets passés en argument.
     */
    public Vector<Computer> trouver(String sql, Object... objets){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Computer computer = null;
        Vector<Computer> computers = new Vector<Computer>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            while ( resultSet.next() ) {
            	computer = map(resultSet);
            	computers.add(computer);
            }
        } catch ( SQLException e ) {
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return computers;
    }
	
	
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des Computers (un
	 * ResultSet) et un bean Computer.
	 */
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
