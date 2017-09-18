package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import view.MainView;

public class Main {
    private static ArrayList<Computer> computers = new ArrayList<Computer>();
    private static ArrayList<Company> companies = new ArrayList<Company>();

    private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();

    private static Scanner sc = new Scanner(System.in);
    private static String str;

    private Page page;

    // private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * Main function.
     *
     * @param args
     *            table of parameters
     */
    public static void main(String[] args) {

        // LOGGER.trace("Hello World!");
        //
        // String name = "Abhijit";
        // LOGGER.debug("Hi, {}", name);
        // LOGGER.info("Welcome to the HelloWorld example of Logback.");
        // LOGGER.warn("Dummy warning message.");
        // LOGGER.error("Dummy error message.");

        Main instance = new Main();
        boolean flagMenu = true;

        while (flagMenu) {
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

            default:
                System.out.println("Veuillez resaisir");
            }
        }
    }

    /** CONSTRUCTEUR. */
    public Main() {
        // Remplit les listes avec les infos de la BDD
        computers = serviceComputer.getAllComputers();
        companies = serviceCompany.getAllCompanies();
        page = new Page(computers, companies);
    }

    /**
     * LIST COMPUTER.
     */
    public void printComputers() {
        page.printComputers(computers, serviceComputer);
    }

    /** COMPANIES. */
    public void printCompanies() {
        page.printCompanies(companies, serviceCompany);
    }

    /**
     * COMPUTERS details.
     * 
     * @param id
     *            id of the computer to give details
     */
    public void printDetails(long id) {
        Computer computer;
        computer = serviceComputer.getComputer(id);
        MainView.printDetails(computer);
    }

    /** create. */
    public void create() {
        computers = serviceComputer.setComputer(MainView.createComputer(), computers);
    }

    /** update. */
    public void update() {
        Computer c = MainView.updateComputer();
        Computer computer = serviceComputer.getComputer(c.getId());
        computer.setName(c.getName());
        computer.setIntroduced(c.getIntroduced());
        computer.setDiscontinued(c.getDiscontinued());
        computer.setCompanyId(c.getCompanyId());

        serviceComputer.updateComputer(computer);
    }

    /** delete. */
    public void delete() {
        long id = MainView.deleteComputer();
        if (id > 0) {
            serviceComputer.deleteComputer(id, computers);
        }
    }

}
