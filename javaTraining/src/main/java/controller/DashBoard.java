package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import util.Page;

@WebServlet("/dashboard")
public class DashBoard extends HttpServlet {
    // Obligatoire pour la définition d'un servlet
    private static final long serialVersionUID = 1L;

    private static ArrayList<Computer> computers = new ArrayList<Computer>();
    private static ArrayList<Company> companies = new ArrayList<Company>();

    private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();

    private static final String SESSION_COMPUTER = "computers";
    private static final String SESSION_COMPANY = "companies";

    private Page page;

    private static final String VIEW = "/WEB-INF/dashboard.jsp";
    private static final String VIEW_ADD = "/WEB-INF/addComputer.jsp";
    private static final String VIEW_HOME = "/javaTraining/dashboard";

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

        if (request.getParameter("page") != null) {
            page.setComputerPage(Integer.parseInt(request.getParameter("page")));
        }

        if (request.getParameter("search") != null) {
            page.setSearch(request.getParameter("search"));
        }

        if (page.getSearch() != "") {
            request.setAttribute("search", page.getSearch());
            request.setAttribute("size", serviceComputer.getComputerByName(page.getSearch()).size());
            request.setAttribute("currentComputers",
                    page.getComputerSubest(serviceComputer.getComputerByName(page.getSearch()), serviceComputer));
        } else {
            request.setAttribute("size", computers.size());
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
        request.setAttribute("page", page.getComputerPage());
        request.setAttribute("numberOfComputerByPage", page.getNumberOfComputerByPage());
        request.setAttribute("begin", page.getBegin());
        request.setAttribute("end", page.getEnd());

        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_COMPUTER) != null) {
            computers = (ArrayList<Computer>) session.getAttribute(SESSION_COMPUTER);
        }

        if (session.getAttribute(SESSION_COMPANY) != null) {
            companies = (ArrayList<Company>) session.getAttribute(SESSION_COMPANY);
        }

        session.setAttribute(SESSION_COMPUTER, computers);
        session.setAttribute(SESSION_COMPANY, companies);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("selection") != null) {
            String[] select = request.getParameterValues("selection");
            String[] selects = select[0].split(",");
            if (selects != null && selects.length != 0) {
                for (String element : selects) {
                    computers = serviceComputer.deleteComputer(Integer.parseInt(element), computers);
                }
            }
            response.sendRedirect(VIEW_HOME);

        } else {
            request.setAttribute("serviceComputer", serviceComputer);
            request.setAttribute("serviceCompany", serviceCompany);
            request.setAttribute("computers", computers);
            request.setAttribute("companies", companies);
            this.getServletContext().getRequestDispatcher(VIEW_ADD).forward(request, response);
        }

    }

}