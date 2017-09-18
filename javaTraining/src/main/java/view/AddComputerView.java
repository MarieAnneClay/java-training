package view;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

public class AddComputerView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW = "/WEB-INF/add_computer.jsp";
	public static final String VIEW_ACCUEIL = "/WEB-INF/home.jsp";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_INTRODUCED = "introduced";
	public static final String FIELD_DISCONTINUED = "discontinued";
	public static final String FIELD_COMPANY_ID = "companyId";
	
	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
    public static final String CONF_DAO_FACTORY = "daofactory";
	
	public void init() throws ServletException {
        /* Récupération d'une instance de nos DAO Client et Commande */
        this.serviceComputer = new ServiceComputer(( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getComputerDao());
        this.serviceCompany = new ServiceCompany(( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCompanyDao());

    }
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		request.setAttribute("serviceComputer", serviceComputer);
		request.setAttribute("serviceCompany", serviceCompany);
		this.getServletContext().getRequestDispatcher(VIEW).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{		
		String name = request.getParameter(FIELD_NAME);
		Timestamp introduced = null;
		Timestamp discontinued = null;
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(request.getParameter(FIELD_INTRODUCED));
		    discontinued = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(request.getParameter(FIELD_DISCONTINUED));
		    introduced = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		long companyId = Integer.parseInt(request.getParameter(FIELD_COMPANY_ID));
		
		String result = "";
        String errors = "";
    	
        serviceComputer.createComputer(new Computer(name,introduced,discontinued,companyId));
    	//result = "Utilisateur \""+name+"\" crée avec succès.";

        request.setAttribute("serviceComputer", serviceComputer);
		request.setAttribute("serviceCompany", serviceCompany);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.getServletContext().getRequestDispatcher(VIEW_ACCUEIL).forward(request, response );
	}
	
	
}