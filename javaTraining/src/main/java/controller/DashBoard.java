package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Computer;
import service.ServiceComputer;
import util.Page;

@WebServlet("/dashboard")
public class DashBoard extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ServiceComputer serviceComputer = ServiceComputer.getInstance();
    private static ArrayList<Computer> computers = serviceComputer.getComputer();
    static {
        // Sorting
        Collections.sort(computers, new Comparator<Computer>() {
            @Override
            public int compare(Computer computer2, Computer computer1) {

                return computer2.getName().compareTo(computer1.getName());
            }
        });
    }

    private static final String VIEW = "/WEB-INF/dashboard.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page page = new Page();
        request = page.getRequest(request);
        ArrayList<Computer> computersPage = new ArrayList<Computer>();
        int nbComputerPage = Integer.parseInt(page.getMap().get("numberOfComputerByPage"));
        int beginIndex = Integer.parseInt(page.getMap().get("currentPage")) * nbComputerPage;
        int endIndex = beginIndex + nbComputerPage;
        int index = 0;
        String search = page.getMap().get("search");

        if (search.equals("")) {
            for (index = beginIndex; index < endIndex; index++) {
                computersPage.add(computers.get(index));
            }

        } else {
            computersPage = serviceComputer.getComputerByName(search, nbComputerPage);

        }
        request.setAttribute("computers", computersPage);

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