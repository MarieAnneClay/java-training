package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.ServiceCompany;
import service.ServiceComputer;
import util.Page;

@Controller
@RequestMapping("/dashboard")
public class DashBoard {
    private ServiceComputer serviceComputer;
    private ServiceCompany serviceCompany;
    private static final String VIEW = "DashBoard";

    @Autowired
    public void setServiceComputer(ServiceComputer serviceComputer) {
        this.serviceComputer = serviceComputer;
    }

    @Autowired
    public void setServiceCompany(ServiceCompany serviceCompany) {
        this.serviceCompany = serviceCompany;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(HttpServletRequest request) {
        Page page = new Page(serviceComputer, serviceCompany);
        request = page.getRequest(request);
        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@RequestParam(value = "selection", required = true) String idsSelects) throws ServletException {
        if (idsSelects != null) {
            serviceComputer.deleteComputer(idsSelects);

        } else {
            throw new ServletException("Illegal exception");
        }
        return VIEW;

    }

}