package view;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Main;
import model.Computer;

public class HomeView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW = "/WEB-INF/home.jsp";
	public static final String VIEW_UPDATE = "/WEB-INF/update_computer.jsp";
	public static final int PAGE_SIZE_COMPUTER = 11;
	public static final int PAGE_SIZE_COMPANY = 15;
	private long id = 0;

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Main mainController = Main.getInstance(); //recuperation du controlleur
		int computerPage = 1;	//page des membres a afficher
		int computerTotalPages = 1 + mainController.getComputers().size() / PAGE_SIZE_COMPUTER;
		int companyPage = 1;	//page des companies a afficher
		int companyTotalPages = 1 + mainController.getCompanies().size() / PAGE_SIZE_COMPANY;
		
		
		
		//gestion des parametres recus dans la requete
		if (request.getParameter("computerPage") != null) {
			computerPage = Integer.parseInt(request.getParameter("computerPage"));
		}			
		
		if (request.getParameter("companyPage") != null) {
			companyPage = Integer.parseInt(request.getParameter("companyPage"));
		}		
		
		if (id == 0 && request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			Computer computer = mainController.getComputer(id);
			if (computer != null) {
				mainController.deleteComputer(computer);
			}			
		}		
		
		//transmission des parametres a la jsp
		request.setAttribute("controller", mainController);
		request.setAttribute("currentComputers", mainController.getComputerSubest( (computerPage - 1) * PAGE_SIZE_COMPUTER, PAGE_SIZE_COMPUTER));
		request.setAttribute("currentCompanies", mainController.getCompanySubest( (companyPage - 1) * PAGE_SIZE_COMPANY, PAGE_SIZE_COMPANY));
		request.setAttribute("computerTotalPages", computerTotalPages);
		request.setAttribute("companyTotalPages", companyTotalPages);
		request.setAttribute("computerPage", computerPage);
		request.setAttribute("companyPage", companyPage);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward( request, response );
    }
}