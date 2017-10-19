package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DTO.CompanyMapper;
import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import validator.ComputerValidator;

@Controller
@RequestMapping("/addcomputer")
public class AddComputer {
    private final ServiceComputer serviceComputer;
    private final ServiceCompany serviceCompany;

    @Autowired
    public AddComputer(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
        super();
        this.serviceComputer = serviceComputer;
        this.serviceCompany = serviceCompany;
    }

    private static final String VIEW = "AddComputer";
    private static final String VIEW_HOME = "dashboard";

    @RequestMapping(method = RequestMethod.GET)
    public String listOfCompanies(ModelMap model) throws ServletException {
        ArrayList<Company> companies = serviceCompany.getAllCompanies();
        model.addAttribute("companies", CompanyMapper.convertCompaniesToDTOS(companies));
        model.addAttribute("computerForm", new Computer());
        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createComputer(Model model, @ModelAttribute("computerForm") Computer computer, BindingResult result) throws ServletException {
        ComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validate(computer, result);

        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.toString());
            }
            model.addAttribute("errors", sb.toString());
            ArrayList<Company> companies = serviceCompany.getAllCompanies();
            model.addAttribute("companies", CompanyMapper.convertCompaniesToDTOS(companies));
            return VIEW;
        } else {
            serviceComputer.setComputer(computer);
            return "redirect:/" + VIEW_HOME;
        }

    }

}