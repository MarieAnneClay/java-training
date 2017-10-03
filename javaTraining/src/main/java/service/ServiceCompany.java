package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import model.Company;
import persistence.daoImpl.CompanyDAOImpl;

public class ServiceCompany {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    private static CompanyDAOImpl companyDAOImpl = CompanyDAOImpl.getInstance();
    private static ServiceCompany INSTANCE = new ServiceCompany();

    public static ServiceCompany getInstance() {
        return INSTANCE;
    }

    /** CONSTRUCTOR.
     * @throws SQLException */
    public ServiceCompany() {
        companyDAOImpl = new CompanyDAOImpl();
    }

    public ArrayList<Company> getAllCompanies() {
        return companyDAOImpl.findAllCompanies();
    }

    /** Function search company by id in the database.
     * @param id id of the searched company
     * @return company searched */
    public Company getCompany(long id) {
        return companyDAOImpl.findByIdCompany(id);
    }
}
