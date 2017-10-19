package controller;

import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private static final String VIEW_HOME = "dashboard";

    @Autowired
    public void setServiceComputer(ServiceComputer serviceComputer) {
        this.serviceComputer = serviceComputer;
    }

    @Autowired
    public void setServiceCompany(ServiceCompany serviceCompany) {
        this.serviceCompany = serviceCompany;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap map, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "numberOfComputerByPage", defaultValue = "10") int numberOfComputerByPage,
            @RequestParam(value = "currentPage", defaultValue = "0") int currentPage, @RequestParam(value = "sort", defaultValue = "cr.name") String sort,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Page page = new Page(search, numberOfComputerByPage, currentPage, sort, order);

        map.addAttribute("size", serviceComputer.getCount(search));
        map.addAttribute("computers", serviceComputer.getComputerByName(search, numberOfComputerByPage, currentPage, sort, page.getOrder()));
        map.addAttribute("serviceCompany", serviceCompany);

        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@RequestParam(value = "selection") ArrayList<Long> idsSelects) throws ServletException {
        serviceComputer.deleteComputer(idsSelects);
        return "redirect:/" + VIEW_HOME;

    }

}