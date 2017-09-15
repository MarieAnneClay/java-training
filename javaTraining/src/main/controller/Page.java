package main.controller;

import java.util.ArrayList;
import java.util.Scanner;

import main.model.Company;
import main.model.Computer;
import main.service.ServiceCompany;
import main.service.ServiceComputer;

public class Page {
	private static final int PAGE_SIZE = 10;
	private static int computerTotalPages;
	private static int companyTotalPages;
	private static Scanner sc = new Scanner(System.in);

	public Page(ArrayList<Computer> computers, ArrayList<Company> companies) {
		computerTotalPages = 1 + computers.size() / PAGE_SIZE;
		companyTotalPages = 1 + companies.size() / PAGE_SIZE;
	}

	/* LIST */
	// COMPUTER
	public void printComputers(ArrayList<Computer> computers, ServiceComputer serviceComputer) {
		boolean flag = false;
		while (flag) {
			System.out.println("Veuillez saisir le numéro de la page souhaité (" + computerTotalPages
					+ " pages) ou 0 pour sortir ");
			int nb = Integer.parseInt(sc.nextLine());
			if (nb > 0 && nb <= computerTotalPages) {
				ArrayList<Computer> computerSubest = serviceComputer.getComputerSubest((nb - 1) * PAGE_SIZE, PAGE_SIZE,
						computers);
				System.out.println("/***** AFFICHAGE DES ORDINATEUR  *****/");
				for (int i = 0; i < computerSubest.size(); i++) {
					Computer computer = computerSubest.get(i);
					System.out.println("ID : " + computer.getId() + " nom : " + computer.getName());
				}
				System.out.println("/***** FIN D'AFFICHAGE DES ORDINATEUR  *****/");
			} else if (nb == 0) {
				flag = true;
			}
		}
	}

	// COMPANIES
	public void printCompanies(ArrayList<Company> companies, ServiceCompany serviceCompany) {
		boolean flag = false;
		while (flag) {
			System.out.println("Veuillez saisir le numéro de la page souhaité (" + companyTotalPages
					+ " pages) ou 0 pour sortir ");
			int nb = Integer.parseInt(sc.nextLine());
			if (nb > 0 && nb <= companyTotalPages) {
				ArrayList<Company> companySubest = serviceCompany.getCompanySubest((nb - 1) * PAGE_SIZE, PAGE_SIZE,
						companies);
				System.out.println("/***** AFFICHAGE DES ENTREPRISES  *****/");
				for (int i = 0; i < companySubest.size(); i++) {
					Company company = companySubest.get(i);
					System.out.println("ID : " + company.getId() + " nom : " + company.getName());
				}
				System.out.println("/***** FIN D'AFFICHAGE DES ENTREPRISES  *****/");
			} else if (nb == 0) {
				flag = true;
			}
		}
	}

}
