package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    private static final String SESSION_COMPUTER = "computers";
    private static final String SESSION_COMPANY = "companies";

    private static final String VIEW = "/WEB-INF/editComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";
    private static final String FIELD_NAME = "computerName";

    private static final String FIELD_INTRODUCED = "introduced";
    private static final String FIELD_DISCONTINUED = "discontinued";
    private static final String FIELD_COMPANY_ID = "companyId";
    private static long id = 0;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        computers = (ArrayList<Computer>) session.getAttribute(SESSION_COMPUTER);
        companies = (ArrayList<Company>) session.getAttribute(SESSION_COMPANY);

        if (request.getParameter("computerId") != null) {
            this.id = request.getParameter("computerId") == "" ? 0
                    : Integer.parseInt(request.getParameter("computerId"));
        }

        request.setAttribute("companies", companies);
        request.setAttribute("computer", serviceComputer.getComputer(id));

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter(FIELD_NAME);
        LocalDate introduced = request.getParameter(FIELD_INTRODUCED).equals("") ? null
                : LocalDate.parse(request.getParameter(FIELD_INTRODUCED));

        LocalDate discontinued = request.getParameter(FIELD_DISCONTINUED).equals("") ? null
                : LocalDate.parse(request.getParameter(FIELD_DISCONTINUED));
        String companyId = request.getParameter(FIELD_COMPANY_ID);

        Computer computer = serviceComputer.getComputer(id);
        computer.setName(name);
        computer.setIntroduced(introduced);
        computer.setDiscontinued(discontinued);
        computer.setCompanyId(companyId == "" ? 0 : Integer.parseInt(companyId));

        computers = serviceComputer.updateComputer(computer, computers);

        request.setAttribute("computers", computers);
        session.setAttribute(SESSION_COMPUTER, computers);
        session.setAttribute(SESSION_COMPANY, companies);
        response.sendRedirect(VIEW_HOME);
    }

}