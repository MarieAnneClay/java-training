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

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();

    private static final String VIEW = "/WEB-INF/addComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

    private static final String FIELD_NAME = "computerName";
    private static final String FIELD_INTRODUCED = "introduced";
    private static final String FIELD_DISCONTINUED = "discontinued";
    private static final String FIELD_COMPANY_ID = "companyId";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("companies", serviceCompany.getAllCompanies());
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession();
        LocalDate introducedDate = null;
        LocalDate discontinuedDate = null;
        String name = StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_NAME));
        StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_INTRODUCED));
        StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_DISCONTINUED));
        String companyId = StringEscapeUtils.unescapeHtml(request.getParameter(FIELD_COMPANY_ID));

        try {
            serviceComputer.setComputer(new Computer(name, introducedDate, discontinuedDate, (companyId == "" ? 0 : Integer.parseInt(companyId))));
            response.sendRedirect(VIEW_HOME);
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }

    }

}