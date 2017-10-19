package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Company;
import persistence.daoImpl.CompanyDAOImpl;

@Service
@Transactional
public class ServiceCompany {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    private final CompanyDAOImpl companyDAOImpl;

    @Autowired
    public ServiceCompany(CompanyDAOImpl companyDAOImpl) {
        super();
        this.companyDAOImpl = companyDAOImpl;
    }

    public ArrayList<Company> getAllCompanies() {
        return companyDAOImpl.findAllCompanies();
    }

    /** Function search company by id in the database.
     * @param id id of the searched company
     * @return company searched */
    public Company getCompany(Long id) {
        return companyDAOImpl.findByIdCompany(id);
    }

}
