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

public class UpdateComputerView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW = "/WEB-INF/update_computer.jsp";
	public static final String VIEW_ACCUEIL = "/WEB-INF/home.jsp";
	
	public static final String FIELD_NAME = "name";
	public static final String FIELD_INTRODUCED = "introduced";
	public static final String FIELD_DISCONTINUED = "discontinued";
	public static final String FIELD_COMPANY_ID = "companyId";
	
	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
    public static final String CONF_DAO_FACTORY      = "daofactory";

	private long id;
	
	public void init() throws ServletException {

        /* Récupération d'une instance de nos DAO */
        this.serviceComputer = new ServiceComputer(( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getComputerDao());
        this.serviceCompany = new ServiceCompany(( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCompanyDao());

    }
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		request.setAttribute("serviceComputer", serviceComputer);
		request.setAttribute("serviceCompany", serviceCompany);
		id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("name", serviceComputer.getComputer(id).getName());
		request.setAttribute("introduced", serviceComputer.getComputer(id).getIntroduced());
		request.setAttribute("discontinued", serviceComputer.getComputer(id).getDiscontinued());
		request.setAttribute("companyId", serviceComputer.getComputer(id).getCompanyId());
		
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
		} catch(Exception e) { 
		}
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(request.getParameter(FIELD_DISCONTINUED));
		    introduced = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { 
		}
		long companyId = Integer.parseInt(request.getParameter(FIELD_COMPANY_ID));
		
		String result = "";
        String errors = "";
        
        Computer computer = serviceComputer.getComputer(id);
        computer.setName(name);
        computer.setIntroduced(introduced);        
        computer.setDiscontinued(discontinued);
        computer.setCompanyId(companyId);
    	
        serviceComputer.updateComputer(computer);

        request.setAttribute("serviceComputer", serviceComputer);
		request.setAttribute("serviceCompany", serviceCompany);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response );
	}
}