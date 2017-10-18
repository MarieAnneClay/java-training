package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DTO.CompanyMapper;
import DTO.ComputerMapper;
import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import validator.ComputerValidator;

@Controller
@RequestMapping("/EditComputer")
public class EditComputer {
    private static final String VIEW = "EditComputer";
    private static final String VIEW_HOME = "dashboard";
    private ServiceCompany serviceCompany;
    private ServiceComputer serviceComputer;

    @Autowired
    public void setServiceCompany(ServiceCompany serviceCompany) {
        this.serviceCompany = serviceCompany;
    }

    @Autowired
    public void setServiceComputer(ServiceComputer serviceComputer) {
        this.serviceComputer = serviceComputer;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam(value = "computerId", required = true) Long id) throws ServletException {
        if (id != null) {
            model.addAttribute("id", id);
            model.addAttribute("computer", ComputerMapper.convertComputerToDTO(serviceComputer.getComputer(id)));
        }
        ArrayList<Company> companies = serviceCompany.getAllCompanies();
        model.addAttribute("companies", CompanyMapper.convertCompaniesToDTOS(companies));
        model.addAttribute("computerForm", new Computer());

        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(Model model, @ModelAttribute("computerForm") Computer computer, BindingResult result, HttpServletRequest request) throws ServletException {
        ComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validate(computer, result);

        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.toString());
            }
            ArrayList<Company> companies = serviceCompany.getAllCompanies();
            model.addAttribute("companies", CompanyMapper.convertCompaniesToDTOS(companies));
            model.addAttribute("errors", sb.toString());
            return VIEW;
        } else {
            serviceComputer.updateComputer(computer);
            return "redirect:/" + VIEW_HOME;
        }

    }

}