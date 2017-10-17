package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.ServiceComputer;
import util.Page;

@Controller
@RequestMapping("/dashboard")
public class DashBoard {
    @Autowired
    private ServiceComputer serviceComputer;
    private static final String VIEW = "dashboard";

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(HttpServletRequest request) {
        Page page = new Page();
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