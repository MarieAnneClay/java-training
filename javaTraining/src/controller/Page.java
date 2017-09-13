package controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

import dao.CompanyDAOImplements;
import dao.ComputerDAOImplements;
import dao.DAOFactory;
import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

public class Page {
	
	// Une liste par table de la base de données
	private static LinkedList<Computer> computers = new LinkedList<Computer>();
	private static LinkedList<Company> companies = new LinkedList<Company>();
	// Connexion a la base de donnée 
	private static DAOFactory daoFactory = DAOFactory.getInstance();
	
	private static ComputerDAOImplements computerDao = new ComputerDAOImplements( daoFactory );
	private static CompanyDAOImplements companyDao = new CompanyDAOImplements( daoFactory );
	private static ServiceComputer serviceComputer;
    private ServiceCompany serviceCompany;
	private static Scanner sc = new Scanner(System.in);
	public static final int PAGE_SIZE_COMPUTER = 11;
	static int computerPage = 1;
	static int computerTotalPages;
	static String str;
	
	public static void main(String[] args) {
		Page instance=new Page();
		boolean flagMenu = false;
		boolean flagSousMenu1 = false;
		computerTotalPages = 1 + serviceComputer.getComputers().size() / PAGE_SIZE_COMPUTER;
		

		while(flagMenu == false) {
			System.out.println("Menu :");
			System.out.println("0 : sortir");
			System.out.println("1 : afficher ordinateur");
			System.out.println("2 : detail ordinateur");
			System.out.println("3 : afficher entreprise");
			System.out.println("4 : creer ordinateur");
			System.out.println("5 : modifier ordinateur");
			System.out.println("6 : supprimer ordinateur");
			System.out.println("Veuillez saisir le numéro de votre choix");
			str = sc.nextLine();
			switch(str) {
				case "0":
					flagMenu = true;
					System.exit(0);
					break;
				
				case "1":
					while(flagSousMenu1 == false) {
						System.out.println("Veuillez saisir le numéro de la page souhaité ("+computerTotalPages+" pages) ou 0 pour sortir ");
						int nb = Integer.parseInt(sc.nextLine());
						if ( nb > 0 && nb <= computerTotalPages ) {
							computerPage = nb;
							instance.printComputers();
						}
						else if ( nb == 0) {
							flagSousMenu1 = true;
						}
					}
					
					break;
					
				case "2":
					System.out.println("Veuillez saisir l'id de l'ordinateur dont vous voulez des détails");
					str = sc.nextLine();
					instance.printDetails(Integer.parseInt(str));
					break;
					
				case "3":
					instance.printCompanies();
					break;
				
				case "4":					
					instance.create();
					break;
					
				case "5":		
					instance.update();
					break;
					
				case "6":					
					instance.delete();
					break;
					
				default :
					System.out.println("Veuillez resaisir");
			}
		}
		

		
	}

  	/* CONSTRUCTEUR */
  	public Page() {
  		this.serviceComputer = new ServiceComputer(computerDao);
        this.serviceCompany = new ServiceCompany(companyDao);
		// Remplit les listes avec les infos de la BDD
		computers = computerDao.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
		companies = companyDao.trouver("SELECT * FROM company WHERE 1 = ?", "1");		
	}
	
	/* LIST */
	// COMPUTER
	public void printComputers() {
		LinkedList<Computer> computerSubest = serviceComputer.getComputerSubest( (computerPage - 1) * PAGE_SIZE_COMPUTER, PAGE_SIZE_COMPUTER);
		System.out.println("/***** AFFICHAGE DES ORDINATEUR  *****/");
		for(int i = 0 ; i < computerSubest.size() ; i++) {
			Computer computer = computerSubest.get(i);
			System.out.println("ID : "+computer.getId()+" nom : "+computer.getName());
		}
		System.out.println("/***** FIN D'AFFICHAGE DES ORDINATEUR  *****/");
	}
	
	//COMPANIES
	public void printCompanies() {
		System.out.println("/***** AFFICHAGE DES ENTREPRISES  *****/");
		for(int i = 0 ; i < companies.size() ; i++) {
			System.out.println(companies.get(i).getName());
		}	
		System.out.println("/***** FIN D'AFFICHAGE DES ENTREPRISES  *****/");
	}
	
	/* COMPUTERS */
	// details
	public void printDetails(long id) {
		Computer computer;
		computer = computerDao.trouver("SELECT * FROM computer WHERE id = ?", id).get(0);
		System.out.println("/***** AFFICHAGE DES INFOS DU COMPUTER ID : " + computer.getId() + " *****/");
		System.out.println("Nom : "+computer.getName());
		System.out.println("Introduced : "+computer.getIntroduced());
		System.out.println("Discontinued : "+computer.getDiscontinued());
		System.out.println("Company Id : "+computer.getCompanyId());
	}
	
	// create
	public void create() {
		String name = null;
		Timestamp introduced = null;
		Timestamp discontinued = null;
		long companyId = 0;
		
		System.out.println("Veuillez saisir le nom de l'ordinateur");
		name = sc.nextLine();
		
		System.out.println("Veuillez saisir le introduced de l'ordinateur");
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(sc.nextLine());
		    discontinued = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		
		System.out.println("Veuillez saisir le discontinued de l'ordinateur");
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(sc.nextLine());
		    introduced = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		
		System.out.println("Veuillez saisir le nom de l'ordinateur");
		companyId = Integer.parseInt(sc.nextLine());
		
		serviceComputer.createComputer(new Computer(name,introduced,discontinued,companyId));
	}
	
	// update
	public void update() {
		String name = null;
		Timestamp introduced = null;
		Timestamp discontinued = null;
		long companyId = 0;
		long id = 0;
		Computer computer;
		
		System.out.println("Veuillez saisir le id de l'ordinateur");
		id = Integer.parseInt(sc.nextLine());
		
		System.out.println("Veuillez saisir le nom de l'ordinateur");
		name = sc.nextLine();
		
		System.out.println("Veuillez saisir le introduced de l'ordinateur");
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(sc.nextLine());
		    discontinued = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		
		System.out.println("Veuillez saisir le discontinued de l'ordinateur");
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(sc.nextLine());
		    introduced = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		
		System.out.println("Veuillez saisir le nom de l'ordinateur");
		companyId = Integer.parseInt(sc.nextLine());
		
		computer = serviceComputer.getComputer(id);
        computer.setName(name);
        computer.setIntroduced(introduced);        
        computer.setDiscontinued(discontinued);
        computer.setCompanyId(companyId);
    	
        serviceComputer.updateComputer(computer);
	}
	
	// delete
	public void delete() {
		long id = 0;
		Computer computer;
		
		System.out.println("Veuillez saisir le id de l'ordinateur");
		id = Integer.parseInt(sc.nextLine());
		computer = serviceComputer.getComputer(id);
		if (computer != null) {
			serviceComputer.deleteComputer(computer);
		}
	}

}
