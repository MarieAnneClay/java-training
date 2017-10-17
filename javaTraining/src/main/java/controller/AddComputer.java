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
import DTO.ComputerDTO;
import model.Company;
import service.ServiceCompany;
import service.ServiceComputer;
import validator.ComputerDTOValidator;

@Controller
@RequestMapping("/addComputer")
public class AddComputer {
    @Autowired
    private ServiceCompany serviceCompany;
    @Autowired
    private ServiceComputer serviceComputer;

    private static final String VIEW = "addComputer";
    private static final String VIEW_HOME = "dashboard";

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) throws ServletException {
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
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.toString());
            }
            model.addAttribute("errors", sb.toString());
            return VIEW;
        } else {
            serviceComputer.setComputer(computerDTO);
            return VIEW_HOME;
        }

    }

}