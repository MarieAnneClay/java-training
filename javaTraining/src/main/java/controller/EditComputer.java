package controller;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DTO.CompanyMapper;
import DTO.ComputerDTO;
import DTO.ComputerMapper;
import model.Company;
import service.ServiceCompany;
import service.ServiceComputer;
import util.ValidatorException;

@Controller
@RequestMapping("/editComputer")
public class EditComputer {
    private ServiceCompany serviceCompany;
    private ServiceComputer serviceComputer;

    private static final String FIELD_NAME = "computerName";
    private static final String FIELD_INTRODUCED = "introduced";
    private static final String FIELD_DISCONTINUED = "discontinued";
    private static final String FIELD_COMPANY_ID = "companyId";

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
    public String doGet(HttpServletRequest request) throws ServletException {

        if (request.getParameter("computerId") != null) {
            long id = 0;
            id = request.getParameter("computerId") == "" ? 0 : Integer.parseInt(request.getParameter("computerId"));
            request.setAttribute("id", id);
            request.setAttribute("computer", ComputerMapper.convertComputerToDTO(serviceComputer.getComputer(id)));
        }

        ArrayList<Company> companies = serviceCompany.getAllCompanies();
        request.setAttribute("companies", CompanyMapper.convertCompaniesToDTOS(companies));

        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model, @RequestParam(value = FIELD_NAME, required = true) String id, @RequestParam(value = FIELD_NAME, required = true) String name,
            @RequestParam(value = FIELD_INTRODUCED, required = false) String introduced, @RequestParam(value = FIELD_DISCONTINUED, required = false) String discontinued,
            @RequestParam(value = FIELD_COMPANY_ID, required = false) String companyId) throws ServletException {

        try {
            serviceComputer.updateComputer(new ComputerDTO(id, name, introduced, discontinued, companyId));
            return VIEW_HOME;
        } catch (ValidatorException e) {
            model.addAttribute("errors", e.getMessage());
            model.addAttribute("computer", serviceComputer.getComputer(Long.parseLong(id)));
            model.addAttribute("companies", serviceCompany.getAllCompanies());
            model.addAttribute("id", id);
            return VIEW;
        }

    }

}