package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.ComputerMapper;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

@WebServlet("/dashboard")
public class DashBoard extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ServiceComputer serviceComputer = ServiceComputer.getInstance();
    private static ServiceCompany serviceCompany = ServiceCompany.getInstance();

    private static final String VIEW = "/WEB-INF/dashboard.jsp";
    private static final String VIEW_ADD = "/WEB-INF/addComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = "";
        int numberOfComputerByPage = 10;
        int currentPage = 0;

        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }

        if (request.getParameter("numberOfComputerByPage") != null) {
            numberOfComputerByPage = Integer.parseInt(request.getParameter("numberOfComputerByPage"));
        }

        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        ArrayList<Computer> computers = serviceComputer.getComputerSubest(currentPage * numberOfComputerByPage, numberOfComputerByPage, serviceComputer.getComputerByName(search));

        if (search.equals("")) {
            request.setAttribute("size", serviceComputer.getAllComputers().size());
        } else {
            request.setAttribute("size", serviceComputer.getComputerByName(search).size());
        }

        request.setAttribute("computers", ComputerMapper.convertComputersToDTOS(computers));
        request.setAttribute("serviceCompany", serviceCompany);

        request.setAttribute("search", search);
        request.setAttribute("numberOfComputerByPage", numberOfComputerByPage);
        request.setAttribute("currentPage", currentPage);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("selection") != null) {
            String idsSelects = request.getParameter("selection");
            serviceComputer.deleteComputer(idsSelects);
            response.sendRedirect(VIEW_HOME);

        } else {
            this.getServletContext().getRequestDispatcher(VIEW_ADD).forward(request, response);
        }

    }

}