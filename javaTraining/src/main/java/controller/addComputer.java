package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;
import service.ServiceComputer;

public class addComputer extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ArrayList<Computer> computers = new ArrayList<Computer>();
    private static ArrayList<Company> companies = new ArrayList<Company>();

    private static ServiceComputer serviceComputer = new ServiceComputer();

    public static final String VIEW = "/WEB-INF/addComputer.jsp";

    public static final String FIELD_NAME = "computerName";
    public static final String FIELD_INTRODUCED = "introduced";
    public static final String FIELD_DISCONTINUED = "discontinued";
    public static final String FIELD_COMPANY_ID = "companyId";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        computers = (ArrayList<Computer>) request.getAttribute("computers");
        companies = (ArrayList<Company>) request.getAttribute("companies");
        serviceComputer = (ServiceComputer) request.getAttribute("serviceComputer");
        request.setAttribute("companies", companies);
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(FIELD_NAME);
        /*
         * LocalDate introduced = request.getParameter(FIELD_INTRODUCED) == "" ? null :
         * LocalDate.parse(request.getParameter(FIELD_INTRODUCED));
         * 
         * LocalDate discontinued = request.getParameter(FIELD_DISCONTINUED).equals("")
         * ? null : LocalDate.parse(request.getParameter(FIELD_DISCONTINUED));
         */
        String companyId = request.getParameter(FIELD_COMPANY_ID);

        computers = serviceComputer.setComputer(
                new Computer(name, null, null, (companyId == "" ? 0 : Integer.parseInt(companyId))), computers);
        this.getServletContext().setAttribute("computers", computers);
        response.sendRedirect("/javaTraining/dashboard");
        // this.getServletContext().getRequestDispatcher(VIEW).forward(request,
        // response);
    }

}