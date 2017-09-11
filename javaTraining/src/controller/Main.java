package controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

/* Import des models */
import model.Computer;
import model.Company;

/* Import des persistences des models */
import persistence.CompanyDAOImplements;
import persistence.ComputerDAOImplements;

/* Import pour la connexion */
import persistence.DAOFactory;

public class Main {
	// Une liste par table de la base de données
	private static Vector<Computer> computers = new Vector<Computer>();
	private static Vector<Company> companies = new Vector<Company>();
	// Connexion a la base de donnée 
	private static DAOFactory daoFactory = DAOFactory.getInstance();
	
	private static ComputerDAOImplements computerDao;
	private static CompanyDAOImplements companyDao;


	public static void main(String[] args) throws SQLException {
		computerDao = new ComputerDAOImplements( daoFactory );
		companyDao = new CompanyDAOImplements( daoFactory );

		// Remplit les listes avec les infos de la BDD
		computers = computerDao.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
		companies = companyDao.trouver("SELECT * FROM company WHERE 1 = ?", "1");
		/*
		System.out.println("COMPUTERS");
		afficherComputers();
		System.out.println("COMPANIES");
		afficherCompanies();
		
		afficherDetails(12);
		*/
		Computer computer = new Computer();
		computer.setName("ESSAI");
		computer.setCompanyId(1);
		//System.out.println(computer.getCompanyId());
		computerDao.creer(computer);
		
	}
	
	/* LIST */
	// COMPUTER
	public static void afficherComputers() {
		for(int i = 0 ; i < computers.size() ; i++) {
			System.out.println(computers.get(i).getName());
		}		
	}
	
	//COMPANIES
	public static void afficherCompanies() {
		for(int i = 0 ; i < companies.size() ; i++) {
			System.out.println(companies.get(i).getName());
		}		
	}
	
	/* COMPUTERS */
	public static void afficherDetails(long id) {
		Computer computer;
		computer = computerDao.trouver("SELECT * FROM computer WHERE id = ?", id).get(0);
		System.out.println("/***** AFFICHAGE DES INFOS DU COMPUTER ID : " + computer.getId() + " *****/");
		System.out.println("Nom : "+computer.getName());
		System.out.println("Introduced : "+computer.getIntroduced());
		System.out.println("Discontinued : "+computer.getDiscontinued());
		System.out.println("Company Id : "+computer.getCompanyId());
	}

}
