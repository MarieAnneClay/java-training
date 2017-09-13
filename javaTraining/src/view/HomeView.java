package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import dao.DAOFactory;

public class HomeView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW = "/WEB-INF/home.jsp";
	public static final int PAGE_SIZE_COMPUTER = 11;
	public static final int PAGE_SIZE_COMPANY = 15;
	private long id = 0;

    private ServiceComputer serviceComputer;
    private ServiceCompany serviceCompany;
    public static final String CONF_DAO_FACTORY      = "daofactory";
	
	public void init() throws ServletException {

        /* Récupération d'une instance de nos DAO Client et Commande */
        this.serviceComputer = new ServiceComputer(( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getComputerDao());
        this.serviceCompany = new ServiceCompany(( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCompanyDao());

    }

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		int computerPage = 1;	//page des membres a afficher
		int computerTotalPages = 1 + serviceComputer.getComputers().size() / PAGE_SIZE_COMPUTER;
		int companyPage = 1;	//page des companies a afficher
		int companyTotalPages = 1 + serviceCompany.getCompanies().size() / PAGE_SIZE_COMPANY;
		
		
		
		//gestion des parametres recus dans la requete
		if (request.getParameter("computerPage") != null) {
			computerPage = Integer.parseInt(request.getParameter("computerPage"));
		}			
		
		if (request.getParameter("companyPage") != null) {
			companyPage = Integer.parseInt(request.getParameter("companyPage"));
		}		
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			Computer computer = serviceComputer.getComputer(id);
			if (computer != null) {
				serviceComputer.deleteComputer(computer);
			}			
		}		
		
		//transmission des parametres a la jsp
		request.setAttribute("serviceComputer", serviceComputer);
		request.setAttribute("serviceCompany", serviceCompany);
		request.setAttribute("currentComputers", serviceComputer.getComputerSubest( (computerPage - 1) * PAGE_SIZE_COMPUTER, PAGE_SIZE_COMPUTER));
		request.setAttribute("currentCompanies", serviceCompany.getCompanySubest( (companyPage - 1) * PAGE_SIZE_COMPANY, PAGE_SIZE_COMPANY));
		request.setAttribute("computerTotalPages", computerTotalPages);
		request.setAttribute("companyTotalPages", companyTotalPages);
		request.setAttribute("computerPage", computerPage);
		request.setAttribute("companyPage", companyPage);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward( request, response );
    }
}