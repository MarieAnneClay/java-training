package service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Company;
import persistence.CompanyRepository;

@Service
@Transactional
public class ServiceCompany {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    private final CompanyRepository companyRepository;

    @Autowired
    public ServiceCompany(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    /** Function search company by id in the database.
     * @param id id of the searched company
     * @return company searched */
    public Company getCompany(Long id) {
        return companyRepository.getOne(id);
    }

}
