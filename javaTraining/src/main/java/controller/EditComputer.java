package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import util.ValidatorException;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ServiceComputer serviceComputer = ServiceComputer.getInstance();
    private static ServiceCompany serviceCompany = ServiceCompany.getInstance();
    private static final ArrayList<Company> companies = serviceCompany.getAllCompanies();

    private static final String VIEW = "/WEB-INF/editComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "computerName";
    private static final String FIELD_INTRODUCED = "introduced";
    private static final String FIELD_DISCONTINUED = "discontinued";
    private static final String FIELD_COMPANY_ID = "companyId";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("computerId") != null) {
            long id = 0;
            id = request.getParameter("computerId") == "" ? 0 : Integer.parseInt(request.getParameter("computerId"));
            request.setAttribute("id", id);
            request.setAttribute("computer", serviceComputer.getComputer(id));
        }

        request.setAttribute("companies", companies);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(FIELD_ID);
        String name = request.getParameter(FIELD_NAME);
        String introduced = request.getParameter(FIELD_INTRODUCED);
        String discontinued = request.getParameter(FIELD_DISCONTINUED);
        String companyId = request.getParameter(FIELD_COMPANY_ID);

        try {
            serviceComputer.updateComputer(new Computer(id == "" ? 0 : Long.parseLong(id), name, introduced.equals("") ? null : LocalDate.parse(introduced),
                    discontinued.equals("") ? null : LocalDate.parse(discontinued), companyId == "" ? 0 : Long.parseLong(companyId)));
            response.sendRedirect(VIEW_HOME);
        } catch (ValidatorException e) {
            request.setAttribute("errors", e.getMessage());
            request.setAttribute("computer", serviceComputer.getComputer(Long.parseLong(id)));
            request.setAttribute("companies", companies);
            request.setAttribute("id", id);
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }

    }

}