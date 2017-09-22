package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

@WebServlet("/dashboard")
public class DashBoard extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();

    private static final String VIEW = "/WEB-INF/dashboard.jsp";
    private static final String VIEW_ADD = "/WEB-INF/addComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession();
        String search = "";
        int numberOfComputerByPage = 10;
        int page = 1;
        int nbPageShowInPagination = 5;
        int computerTotalPages = 0;

        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }

        if (request.getParameter("numberOfComputerByPage") != null) {
            numberOfComputerByPage = Integer.parseInt(request.getParameter("numberOfComputerByPage"));
        }

        if (request.getParameter("nbPageShowInPagination") != null) {
            nbPageShowInPagination = Integer.parseInt(request.getParameter("nbPageShowInPagination"));
        }

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ArrayList<Computer> computers = serviceComputer.getComputerSubest((page - 1) * numberOfComputerByPage, numberOfComputerByPage, serviceComputer.getComputerByName(search));

        if (search.equals("")) {
            request.setAttribute("size", serviceComputer.getAllComputers().size());
            computerTotalPages = 1 + serviceComputer.getAllComputers().size() / numberOfComputerByPage;
        } else {
            request.setAttribute("size", serviceComputer.getComputerByName(search).size());
            computerTotalPages = 1 + serviceComputer.getComputerByName(search).size() / numberOfComputerByPage;
        }

        request.setAttribute("computers", computers);
        request.setAttribute("serviceCompany", serviceCompany);

        request.setAttribute("search", search);
        request.setAttribute("computerTotalPages", computerTotalPages);
        request.setAttribute("numberOfComputerByPage", numberOfComputerByPage);
        request.setAttribute("nbPageShowInPagination", nbPageShowInPagination);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("selection") != null) {
            String[] select = request.getParameterValues("selection");
            String[] selects = select[0].split(",");
            if (selects != null && selects.length != 0) {
                for (String element : selects) {
                    serviceComputer.deleteComputer(Integer.parseInt(element));
                }
            }
            response.sendRedirect(VIEW_HOME);

        } else {
            this.getServletContext().getRequestDispatcher(VIEW_ADD).forward(request, response);
        }

    }

}