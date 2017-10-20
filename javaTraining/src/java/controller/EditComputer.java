package controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import validator.ComputerValidator;

@Controller
@RequestMapping("/editcomputer")
public class EditComputer {
    private static final String VIEW = "EditComputer";
    private static final String VIEW_HOME = "dashboard";
    private final ServiceCompany serviceCompany;
    private final ServiceComputer serviceComputer;

    @Autowired
    public EditComputer(ServiceCompany serviceCompany, ServiceComputer serviceComputer) {
        super();
        this.serviceCompany = serviceCompany;
        this.serviceComputer = serviceComputer;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String oldComputer(ModelMap model, @RequestParam(value = "computerId", required = true) Long id) throws ServletException {
        if (id != null) {
            model.addAttribute("id", id);
            model.addAttribute("computer", serviceComputer.getComputerById(id));
        }
        List<Company> companies = serviceCompany.getAllCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("computerForm", new Computer());

        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateComputer(Model model, @ModelAttribute("computerForm") Computer computer, BindingResult result) throws ServletException {
        ComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validate(computer, result);

        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.toString());
            }
            List<Company> companies = serviceCompany.getAllCompanies();
            model.addAttribute("companies", companies);
            model.addAttribute("errors", sb.toString());
            return VIEW;
        } else {
            serviceComputer.updateComputer(computer);
            return "redirect:/" + VIEW_HOME;
        }

    }

}