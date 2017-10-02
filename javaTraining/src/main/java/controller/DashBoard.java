package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceComputer;
import util.Page;

@WebServlet("/dashboard")
public class DashBoard extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ServiceComputer serviceComputer = ServiceComputer.getInstance();
    private static final String VIEW = "/WEB-INF/dashboard.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page page = new Page();
        request = page.getRequest(request);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("selection") != null) {
            String idsSelects = request.getParameter("selection");
            serviceComputer.deleteComputer(idsSelects);
            response.sendRedirect(VIEW_HOME);

        } else {
            throw new ServletException("Invalid exception");
        }

    }

}