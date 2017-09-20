package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Company;
import model.Computer;
import service.ServiceComputer;
import util.Validator;

@WebServlet("/addComputer")
public class addComputer extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ArrayList<Computer> computers = new ArrayList<Computer>();
    private static ArrayList<Company> companies = new ArrayList<Company>();

    private static ServiceComputer serviceComputer = new ServiceComputer();

    private static final String VIEW = "/WEB-INF/addComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

    private static final String SESSION_COMPUTER = "computers";
    private static final String SESSION_COMPANY = "companies";

    private static final String FIELD_NAME = "computerName";
    private static final String FIELD_INTRODUCED = "introduced";
    private static final String FIELD_DISCONTINUED = "discontinued";
    private static final String FIELD_COMPANY_ID = "companyId";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        computers = (ArrayList<Computer>) session.getAttribute(SESSION_COMPUTER);
        companies = (ArrayList<Company>) session.getAttribute(SESSION_COMPANY);
        request.setAttribute("companies", companies);
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LocalDate introducedDate = null;
        LocalDate discontinuedDate = null;
        String name = request.getParameter(FIELD_NAME);
        String introduced = request.getParameter(FIELD_INTRODUCED);
        String discontinued = request.getParameter(FIELD_DISCONTINUED);
        String companyId = request.getParameter(FIELD_COMPANY_ID);

        ArrayList<String> errors = new ArrayList<String>();

        try {
            Validator.validationName(name);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        try {
            Validator.validationDate(introduced);
            introducedDate = request.getParameter(FIELD_INTRODUCED).equals("") ? null
                    : LocalDate.parse(request.getParameter(FIELD_INTRODUCED));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
        try {
            Validator.validationDate(discontinued);
            discontinuedDate = request.getParameter(FIELD_DISCONTINUED).equals("") ? null
                    : LocalDate.parse(request.getParameter(FIELD_DISCONTINUED));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        if (errors.isEmpty()) {

            computers = serviceComputer.setComputer(new Computer(name, introducedDate, discontinuedDate,
                    (companyId == "" ? 0 : Integer.parseInt(companyId))), computers);
            session.setAttribute(SESSION_COMPUTER, computers);
            session.setAttribute(SESSION_COMPANY, companies);
            response.sendRedirect(VIEW_HOME);
        } else {
            errors.add("Erreur lors de la création :");
            request.setAttribute("errors", errors);
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }

    }

}