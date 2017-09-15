package main.view;

import java.time.LocalDate;
import java.util.Scanner;

import main.model.Computer;
import main.validator.Validator;

public class MainView {
  private static Scanner sc = new Scanner(System.in);
  private static String str;

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

  // details
  public static void printDetails(Computer computer) {
    System.out.println("/***** AFFICHAGE DES INFOS DU COMPUTER ID : "
        + computer.getId() + " *****/");
    System.out.println("Nom : " + computer.getName());
    System.out.println("Introduced : " + computer.getIntroduced());
    System.out.println("Discontinued : " + computer.getDiscontinued());
    System.out.println("Company Id : " + computer.getCompanyId());
  }

  // create
  public static Computer createComputer() {
    String name = null;
    LocalDate introduced = null;
    LocalDate discontinued = null;
    String companyId = "";

    System.out.println("Veuillez saisir le nom de l'ordinateur");
    name = sc.nextLine();

    System.out.println("Veuillez saisir le introduced de l'ordinateur");
    introduced = Validator.parseStringToLocalDate(sc.nextLine());

    System.out.println("Veuillez saisir le discontinued de l'ordinateur");
    discontinued = Validator.parseStringToLocalDate(sc.nextLine());

    System.out.println("Veuillez saisir l'id de l'entreprise de l'ordinateur");

    return new Computer(name, introduced, discontinued,
        (companyId == "") ? 0 : Integer.parseInt(companyId));
  }

  // update
  public static Computer updateComputer() {
    String name = null;
    LocalDate introduced = null;
    LocalDate discontinued = null;
    String companyId = "";
    long id = 0;
    Computer computer = null;

    System.out.println("Veuillez saisir le id de l'ordinateur");
    id = Integer.parseInt(sc.nextLine());
    name = sc.nextLine();

    System.out.println("Veuillez saisir le introduced de l'ordinateur");
    introduced = Validator.parseStringToLocalDate(sc.nextLine());

    System.out.println("Veuillez saisir le discontinued de l'ordinateur");
    discontinued = Validator.parseStringToLocalDate(sc.nextLine());

    System.out.println("Veuillez saisir l'id de l'entreprise de l'ordinateur");
    companyId = sc.nextLine();
    computer.setId(id);
    computer.setName(name);
    computer.setIntroduced(introduced);
    computer.setDiscontinued(discontinued);
    computer.setCompanyId((companyId == "") ? 0 : Integer.parseInt(companyId));

    return computer;
  }

  // delete
  public static long deleteComputer() {
    long id = 0;

    System.out.println("Veuillez saisir le id de l'ordinateur");
    id = Integer.parseInt(sc.nextLine());
    return id;
  }
}
