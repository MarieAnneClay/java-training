package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();

    private static final String VIEW = "/WEB-INF/editComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";
    private static final String FIELD_NAME = "computerName";

    private static final String FIELD_INTRODUCED = "introduced";
    private static final String FIELD_DISCONTINUED = "discontinued";
    private static final String FIELD_COMPANY_ID = "companyId";
    private static long id = 0;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("computerId") != null) {
            this.id = request.getParameter("computerId") == "" ? 0 : Integer.parseInt(request.getParameter("computerId"));
        }

        request.setAttribute("companies", serviceCompany.getAllCompanies());
        request.setAttribute("computer", serviceComputer.getComputer(id));

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession();
        String name = StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_NAME));
        StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_INTRODUCED));
        StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_DISCONTINUED));
        String companyId = StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_COMPANY_ID));

        Computer computer = serviceComputer.getComputer(id);
        computer.setName(name);
        computer.setIntroduced(request.getParameter(FIELD_INTRODUCED).equals("") ? null : LocalDate.parse(request.getParameter(FIELD_INTRODUCED)));
        computer.setDiscontinued(request.getParameter(FIELD_DISCONTINUED).equals("") ? null : LocalDate.parse(request.getParameter(FIELD_DISCONTINUED)));
        computer.setCompanyId((companyId == "" ? 0 : Integer.parseInt(companyId)));
        try {
            serviceComputer.updateComputer(computer);
            response.sendRedirect(VIEW_HOME);
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }

    }

}