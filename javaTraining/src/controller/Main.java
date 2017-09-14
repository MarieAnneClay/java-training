package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import view.MainView;

public class Main {	
	// Une liste par table de la base de données
	private static ArrayList<Computer> computers = new ArrayList<Computer>();
	private static ArrayList<Company> companies = new ArrayList<Company>();

	private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();
    
	private static Scanner sc = new Scanner(System.in);
	private static String str;
	
	public static final int PAGE_SIZE= 10;
	private static int computerTotalPages;
	private static int companyTotalPages;

	
	public static void main(String[] args) {
		Main instance = new Main();
		boolean flagMenu = false;
		computerTotalPages = 1 + computers.size() / PAGE_SIZE;
		companyTotalPages = 1 + companies.size() / PAGE_SIZE;
		

		while (flagMenu == false) {
			str = MainView.afficherMenu();
			switch (str) {
				case "0":
					flagMenu = true;
					System.exit(0);
					break;
				
				case "1":
					instance.printComputers();					
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
  	public Main () {  		
		// Remplit les listes avec les infos de la BDD
		computers = serviceComputer.getAllComputers();
		companies = serviceCompany.getAllCompanies();		
	}
	
	/* LIST */
	// COMPUTER
	public void printComputers () {
		boolean flag = false;
		while (flag == false) {
			System.out.println("Veuillez saisir le numéro de la page souhaité ("+computerTotalPages+" pages) ou 0 pour sortir ");
			int nb = Integer.parseInt(sc.nextLine());
			if ( nb > 0 && nb <= computerTotalPages ) {
				ArrayList<Computer> computerSubest = serviceComputer.getComputerSubest( (nb - 1) * PAGE_SIZE, PAGE_SIZE, computers);
				System.out.println("/***** AFFICHAGE DES ORDINATEUR  *****/");
				for(int i = 0 ; i < computerSubest.size() ; i++) {
					Computer computer = computerSubest.get(i);
					System.out.println("ID : "+computer.getId()+" nom : "+computer.getName());
				}
				System.out.println("/***** FIN D'AFFICHAGE DES ORDINATEUR  *****/");
			}
			else if ( nb == 0) {
				flag = true;
			}
		}	
	}
	
	//COMPANIES
	public void printCompanies () {
		boolean flag = false;
		while (flag == false) {
			System.out.println("Veuillez saisir le numéro de la page souhaité ("+companyTotalPages+" pages) ou 0 pour sortir ");
			int nb = Integer.parseInt(sc.nextLine());
			if ( nb > 0 && nb <= companyTotalPages ) {
				ArrayList<Company> companySubest = serviceCompany.getCompanySubest( (nb - 1) * PAGE_SIZE, PAGE_SIZE, companies);
				System.out.println("/***** AFFICHAGE DES ENTREPRISES  *****/");
				for(int i = 0 ; i < companySubest.size() ; i++) {
					Company company = companySubest.get(i);
					System.out.println("ID : "+company.getId()+" nom : "+company.getName());
				}	
				System.out.println("/***** FIN D'AFFICHAGE DES ENTREPRISES  *****/");
			}
			else if ( nb == 0) {
				flag = true;
			}
		}		
	}
	
	/* COMPUTERS */
	// details
	public void printDetails (long id) {
		Computer computer;
		computer = serviceComputer.getComputer(id);
		MainView.printDetails (computer);
	}
	
	// create
	public void create () {	
		computers = serviceComputer.setComputer(MainView.createComputer(), computers );
	}
	
	// update
	public void update() {
		Computer c = MainView.updateComputer();
		Computer computer = serviceComputer.getComputer(c.getId());
        computer.setName(c.getName());
        computer.setIntroduced(c.getIntroduced());        
        computer.setDiscontinued(c.getDiscontinued());
        computer.setCompanyId(c.getCompanyId());
    	
        serviceComputer.updateComputer(computer);
	}
	
	// delete
	public void delete() {
		long id = MainView.deleteComputer();
		if ( id > 0 ) {
			serviceComputer.deleteComputer(id,computers);
		}
	}

}
