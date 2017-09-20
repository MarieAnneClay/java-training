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

public class DashBoard extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ArrayList<Computer> computers = new ArrayList<Computer>();
    private static ArrayList<Company> companies = new ArrayList<Company>();

    private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();

    private Page page;

    private static final String VIEW = "/WEB-INF/dashboard.jsp";
    private static String search = "";

    @Override
    public void init() throws ServletException {
        computers = serviceComputer.getAllComputers();
        companies = serviceCompany.getAllCompanies();
        page = new Page(computers);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("numberOfComputerByPage") != null) {
            page.setNumberOfComputerByPage(Integer.parseInt(request.getParameter("numberOfComputerByPage")));
        }

        if (request.getParameter("computerPage") != null) {
            page.setComputerPage(Integer.parseInt(request.getParameter("computerPage")));
        }

        if (request.getParameter("search") != null || request.getAttribute("search") != null) {
            if (request.getParameter("search") != null) {
                search = request.getParameter("search");
            } else {
                search = (String) request.getAttribute("search");
            }

            request.setAttribute("search", search);
            request.setAttribute("currentComputers",
                    page.getComputerSubest(serviceComputer.getComputerByName(search), serviceComputer));
        } else {
            request.setAttribute("currentComputers", page.getComputerSubest(computers, serviceComputer));
        }

        if (request.getAttribute("computers") != null) {
            this.computers = (ArrayList<Computer>) request.getAttribute("computers");
        }

        request.setAttribute("serviceComputer", serviceComputer);
        request.setAttribute("serviceCompany", serviceCompany);
        request.setAttribute("computers", computers);
        request.setAttribute("companies", companies);

        request.setAttribute("computerTotalPages", page.getComputerTotalPages());
        request.setAttribute("computerPage", page.getComputerPage());
        request.setAttribute("numberOfComputerByPage", page.getNumberOfComputerByPage());
        request.setAttribute("begin", page.getBegin());
        request.setAttribute("end", page.getEnd());

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("serviceComputer", serviceComputer);
        request.setAttribute("serviceCompany", serviceCompany);
        request.setAttribute("computers", computers);
        request.setAttribute("companies", companies);
        this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
    }

}