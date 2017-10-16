package controller;

import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DTO.CompanyMapper;
import DTO.ComputerDTO;
import model.Company;
import service.ServiceCompany;
import service.ServiceComputer;
import validator.ComputerDTOValidator;

@Controller
@RequestMapping("/addComputer")
public class AddComputer {
    private ServiceCompany serviceCompany;
    private ServiceComputer serviceComputer;

    private static final String VIEW = "addComputer";
    private static final String VIEW_HOME = "dashboard";

    @Autowired
    public ServiceComputer setServiceComputer(ServiceComputer serviceComputer) {
        return this.serviceComputer = serviceComputer;
    }

    @Autowired
    public ServiceCompany setServiceCompany(ServiceCompany serviceCompany) {
        return this.serviceCompany = serviceCompany;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) throws ServletException {
        ArrayList<Company> companies = serviceCompany.getAllCompanies();
        model.addAttribute("companies", CompanyMapper.convertCompaniesToDTOS(companies));
        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(Model model, @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result) throws ServletException {
        ComputerDTOValidator computerDTOValidator = new ComputerDTOValidator();
        computerDTOValidator.validate(computerDTO, result);

        if (result.hasErrors()) {
            ArrayList<String> errors = (ArrayList<String>) result.getAllErrors();
            for (String error : errors) {
                model.addAttribute("errors", error);
            }
            return VIEW;
        } else {
            serviceComputer.setComputer(computerDTO);
            return VIEW_HOME;
        }

    }

}