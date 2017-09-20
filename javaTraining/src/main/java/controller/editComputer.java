package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

public class editComputer extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ArrayList<Computer> computers = new ArrayList<Computer>();
    private static ArrayList<Company> companies = new ArrayList<Company>();

    private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();

    private Page page;

    private static final String VIEW = "/WEB-INF/editComputer.jsp";
    private static final String FIELD_NAME = "computerName";
    private static final String FIELD_COMPANY_ID = "companyId";
    private static long id = 0;

    @Override
    public void init() throws ServletException {
        companies = serviceCompany.getAllCompanies();
        page = new Page(computers);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("computerId") != null) {
            this.id = request.getParameter("computerId") == "" ? 0
                    : Integer.parseInt(request.getParameter("computerId"));
        }

        computers = (ArrayList<Computer>) request.getAttribute("computers");
        request.setAttribute("computer", serviceComputer.getComputer(id));
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

        Computer computer = serviceComputer.getComputer(id);
        computer.setName(name);
        computer.setIntroduced(null);
        computer.setDiscontinued(null);
        computer.setCompanyId(companyId == "" ? 0 : Integer.parseInt(companyId));

        serviceComputer.updateComputer(computer);

        request.setAttribute("computers", computers);
        response.sendRedirect("/javaTraining/dashboard");
    }

}