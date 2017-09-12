package view;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Main;
import model.Computer;

public class UpdateComputerView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW = "/WEB-INF/update_computer.jsp";
	public static final String VIEW_ACCUEIL = "/WEB-INF/home.jsp";
	
	public static final String FIELD_NAME = "name";
	public static final String FIELD_INTRODUCED = "introduced";
	public static final String FIELD_DISCONTINUED = "discontinued";
	public static final String FIELD_COMPANY_ID = "companyId";
	
	public static Main mainController = Main.getInstance();
	private long id;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		request.setAttribute("controller", mainController);
		id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("name", mainController.getComputer(id).getName());
		request.setAttribute("introduced", mainController.getComputer(id).getIntroduced());
		request.setAttribute("discontinued", mainController.getComputer(id).getDiscontinued());
		request.setAttribute("companyId", mainController.getComputer(id).getCompanyId());
		
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
        Computer computer = mainController.getComputer(id);
    	
		mainController.updateComputer(computer);
    	//result = "Utilisateur \""+name+"\" crée avec succès.";

		request.setAttribute("controller", mainController);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response );
	}
}