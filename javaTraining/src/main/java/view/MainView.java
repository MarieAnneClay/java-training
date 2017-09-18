package view;

import java.sql.Timestamp;
import java.util.Scanner;

import model.Computer;

public class MainView {
    private static Scanner sc = new Scanner(System.in);
    private static String str;

    /**
     * Function print the main menu.
     * 
     * @return String the user text
     */
    public static String afficherMenu() {
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
        return str;
    }

    /**
     * Function print the details of the selected computer.
     * 
     * @param computer
     *            the selected computer to print
     */
    public static void printDetails(Computer computer) {
        System.out.println("/***** AFFICHAGE DES INFOS DU COMPUTER ID : " + computer.getId() + " *****/");
        System.out.println("Nom : " + computer.getName());
        System.out.println("Introduced : " + computer.getIntroduced());
        System.out.println("Discontinued : " + computer.getDiscontinued());
        System.out.println("Company Id : " + computer.getCompanyId());
    }

    /**
     * Function get the information from the user to create a new computer.
     * 
     * @return Computer the created computer by the user
     */
    public static Computer createComputer() {
        String name = null;
        Timestamp introduced = null;
        Timestamp discontinued = null;
        String companyId = "";

        System.out.println("Veuillez saisir le nom de l'ordinateur");
        name = sc.nextLine();

        System.out.println("Veuillez saisir le introduced de l'ordinateur");
        introduced = Timestamp.valueOf(sc.nextLine());

        System.out.println("Veuillez saisir le discontinued de l'ordinateur");
        discontinued = Timestamp.valueOf(sc.nextLine());

        System.out.println("Veuillez saisir l'id de l'entreprise de l'ordinateur");

        return new Computer(name, introduced, discontinued, (companyId == "") ? 0 : Integer.parseInt(companyId));
    }

    /**
     * Function get the information from the user to update a computer.
     * 
     * @return Computer the updated computer by the user
     */
    public static Computer updateComputer() {
        String name = null;
        Timestamp introduced = null;
        Timestamp discontinued = null;
        String companyId = "";
        long id = 0;
        Computer computer = null;

        System.out.println("Veuillez saisir le id de l'ordinateur");
        id = Integer.parseInt(sc.nextLine());
        name = sc.nextLine();

        System.out.println("Veuillez saisir le introduced de l'ordinateur");
        introduced = Timestamp.valueOf(sc.nextLine());

        System.out.println("Veuillez saisir le discontinued de l'ordinateur");
        discontinued = Timestamp.valueOf(sc.nextLine());

        System.out.println("Veuillez saisir l'id de l'entreprise de l'ordinateur");
        companyId = sc.nextLine();
        computer.setId(id);
        computer.setName(name);
        computer.setIntroduced(introduced);
        computer.setDiscontinued(discontinued);
        computer.setCompanyId((companyId == "") ? 0 : Integer.parseInt(companyId));

        return computer;
    }

    /**
     * Function get the id from the user to delete a computer.
     * 
     * @return id the id of the deleted computer by the user
     */
    public static long deleteComputer() {
        long id = 0;

        System.out.println("Veuillez saisir le id de l'ordinateur");
        id = Integer.parseInt(sc.nextLine());
        return id;
    }
}
