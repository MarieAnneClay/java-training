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
import org.springframework.web.bind.annotation.RequestParam;

import DTO.CompanyMapper;
import DTO.ComputerDTO;
import DTO.ComputerMapper;
import model.Company;
import service.ServiceCompany;
import service.ServiceComputer;
import validator.ComputerDTOValidator;

@Controller
@RequestMapping("/editComputer")
public class EditComputer {
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
    public String doGet(ModelMap model, @RequestParam(value = "computerId", required = true) Long id) throws ServletException {
        if (id != null) {
            model.addAttribute("id", id);
            model.addAttribute("computer", ComputerMapper.convertComputerToDTO(serviceComputer.getComputer(id)));
        }

        ArrayList<Company> companies = serviceCompany.getAllCompanies();
        model.addAttribute("companies", CompanyMapper.convertCompaniesToDTOS(companies));

        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(Model model, @ModelAttribute("computerForm") ComputerDTO computerDTO, BindingResult result) throws ServletException {
        ComputerDTOValidator computerDTOValidator = new ComputerDTOValidator();
        computerDTOValidator.validate(computerDTO, result);

        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> errors = (ArrayList<String>) result.getAllErrors();
            for (String error : errors) {
                sb.append(error);
            }
            model.addAttribute("errors", sb.toString());
            return VIEW;
        } else {
            serviceComputer.updateComputer(computerDTO);
            return VIEW_HOME;
        }

    }

}