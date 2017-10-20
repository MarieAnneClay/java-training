package controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.ServiceCompany;
import service.ServiceComputer;

@Controller
@RequestMapping("/dashboard")
public class DashBoard {
    private final ServiceComputer serviceComputer;
    private final ServiceCompany serviceCompany;
    private static final String VIEW = "DashBoard";
    private static final String VIEW_HOME = "dashboard";

    @Autowired
    public DashBoard(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
        super();
        this.serviceComputer = serviceComputer;
        this.serviceCompany = serviceCompany;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listOfComputers(ModelMap map, @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "numberOfComputerByPage", defaultValue = "10") int numberOfComputerByPage, @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
            @RequestParam(value = "sort", defaultValue = "cr.name") String sort, @RequestParam(value = "order", defaultValue = "ASC") String order) {
        // Page page = new Page(search, numberOfComputerByPage, currentPage, sort,
        // order);
        map.addAttribute("search", search);
        map.addAttribute("numberOfComputerByPage", numberOfComputerByPage);
        map.addAttribute("currentPage", currentPage);
        map.addAttribute("sort", sort);
        map.addAttribute("order", order);
        map.addAttribute("size", serviceComputer.getCount(search));
        // map.addAttribute("computers", serviceComputer.getComputerByName(search,
        // numberOfComputerByPage, currentPage, sort, page.getOrder()));
        map.addAttribute("computers", serviceComputer.getComputerByName(PageRequest.of(currentPage, numberOfComputerByPage), search).getContent());
        map.addAttribute("serviceCompany", serviceCompany);

        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String deleteComputers(@RequestParam(value = "selection") String idsSelects) throws ServletException {
        serviceComputer.deleteComputer(idsSelects);
        return "redirect:/" + VIEW_HOME;

    }

}