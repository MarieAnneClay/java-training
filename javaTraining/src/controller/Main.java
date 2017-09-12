package controller;

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
	
	private static ComputerDAOImplements computerDao = new ComputerDAOImplements( daoFactory );
	private static CompanyDAOImplements companyDao = new CompanyDAOImplements( daoFactory );
	
	//implémentation du singleton
  	/** Holder */
  	private static class SingletonHolder
  	{		
  		/** Instance unique non préinitialisée */
  			private final static Main instance=new Main();
  	}
   
  	/** Point d'accès pour l'instance unique du singleton */
  	public static Main getInstance()
  	{
  		return SingletonHolder.instance;
  	}


  	/* CONSTRUCTEUR */
  	public Main() {
		// Remplit les listes avec les infos de la BDD
		computers = computerDao.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
		companies = companyDao.trouver("SELECT * FROM company WHERE 1 = ?", "1");		
	}
  	
  	/* GETTERS & SETTERS */
  	// COMPUTER
  	public Vector<Computer> getComputers(){
  		return computers;
  	}
  	
  	public Computer getComputer(long id){
  		Computer computer = computerDao.trouver("SELECT * FROM computer WHERE id = ?", id).get(0);  		
  		return computer;
  	}
  	
  	public void setComputers(Vector<Computer> computers) {
  		this.computers = computers;
  	}
  	
  	public void updateComputer(Computer computer) {
  		computerDao.modifier(computer);
  	}
  	
  	public void deleteComputer(Computer computer) {
  		computerDao.supprimer(computer);
		computers.removeAllElements();
		computers = computerDao.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
  	}
  	
  	public Vector<Computer> getComputerSubest(int start, int size) {
		Vector<Computer> ret = new Vector<Computer>();
		for (int i = start ; i < (start+size) ; i++) {
			if ( i >= computers.size() ) {
				break;
			}
			ret.add(computers.get(i));
		}
		return ret;
	}
  	
  	public void createComputer(Computer computer) {
  		computerDao.creer(computer);
  		computers = computerDao.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
  	}
  	
  	// COMPANY
   	public Vector<Company> getCompanies(){
   		return companies;
   	}
   	
   	public Company getCompany(long id){
   		Company company = companyDao.trouver("SELECT * FROM company WHERE id = ?", id).get(0);  		
  		return company;
  	}
   	
   	public void setCompanies(Vector<Company> companies) {
   		this.companies = companies;
   	}
   	
   	public Vector<Company> getCompanySubest(int start, int size) {
		Vector<Company> ret = new Vector<Company>();
		for (int i = start ; i < (start+size) ; i++) {
			if ( i >= companies.size() ) {
				break;
			}
			ret.add(companies.get(i));
		}
		return ret;
	}
	
	/* LIST */
	// COMPUTER
	public void afficherComputers() {
		for(int i = 0 ; i < computers.size() ; i++) {
			System.out.println(computers.get(i).getName());
		}		
	}
	
	//COMPANIES
	public void afficherCompanies() {
		for(int i = 0 ; i < companies.size() ; i++) {
			System.out.println(companies.get(i).getName());
		}		
	}
	
	/* COMPUTERS */
	public void afficherDetails(long id) {
		Computer computer;
		computer = computerDao.trouver("SELECT * FROM computer WHERE id = ?", id).get(0);
		System.out.println("/***** AFFICHAGE DES INFOS DU COMPUTER ID : " + computer.getId() + " *****/");
		System.out.println("Nom : "+computer.getName());
		System.out.println("Introduced : "+computer.getIntroduced());
		System.out.println("Discontinued : "+computer.getDiscontinued());
		System.out.println("Company Id : "+computer.getCompanyId());
	}

}
