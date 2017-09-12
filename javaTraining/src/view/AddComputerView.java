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

public class AddComputerView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW = "/WEB-INF/add_computer.jsp";
	public static final String VIEW_ACCUEIL = "/WEB-INF/home.jsp";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_INTRODUCED = "introduced";
	public static final String FIELD_DISCONTINUED = "discontinued";
	public static final String FIELD_COMPANY_ID = "companyId";
	public static Main mainController = Main.getInstance();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		request.setAttribute("controller", mainController);
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
    	
		mainController.createComputer(new Computer(name,introduced,discontinued,companyId));
    	//result = "Utilisateur \""+name+"\" crée avec succès.";

		request.setAttribute("controller", mainController);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.getServletContext().getRequestDispatcher(VIEW_ACCUEIL).forward(request, response );
	}
	
	private void validationName(String name) throws Exception {
		if (name == null || name.trim().length() == 0)
    		throw new Exception("Le nom du nouveau membre ne peut pas être vide");
	}
	
	private void validationEmail(String email) throws Exception {
		if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "L'addresse email n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "L'addresse email ne peut pas être vide" );
	    }
	}
	
	private void validationBirthdate(String birthdate) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
		if ( birthdate != null && birthdate.trim().length() != 0 ) {
	        try {
	        	fmt.parse(birthdate);
	        } catch (ParseException e) {
	        	throw new Exception( "Le format de la date de naissance n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "La date de naissance ne peut pas être vide" );
	    }
	}
	
	private void validationPromo(String promo) throws Exception {
		
	}
}