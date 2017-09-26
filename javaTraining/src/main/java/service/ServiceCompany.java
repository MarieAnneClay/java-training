package service;

import java.util.ArrayList;

import model.Company;
import persistence.daoImpl.CompanyDAOImpl;
import persistence.daoUtil.ConnectionManager;

public class ServiceCompany {
    private CompanyDAOImpl companyDAOImpl;

    /** CONSTRUCTOR. */
    public ServiceCompany() {
        this.companyDAOImpl = new CompanyDAOImpl(ConnectionManager.getInstance());
    }

    public ArrayList<Company> getAllCompanies() {
        return companyDAOImpl.findAllCompanies();
    }

    /** Function search company by id in the database.
     * @param id id of the searched company
     * @return company searched */
    public Company getCompany(long id) {
        return this.companyDAOImpl.findByIdCompany(id);
    }

    /** Function to have a certain proportion of the company list.
     * @param start start of the list
     * @param size size of the list
     * @param companies list of all companies
     * @return ArrayList of the subsided company */
    public ArrayList<Company> getCompanySubest(int start, int size, ArrayList<Company> companies) {
        ArrayList<Company> ret = new ArrayList<Company>();
        for (int i = start; i < (start + size); i++) {
            if (i >= companies.size()) {
                break;
            }
            ret.add(companies.get(i));
        }
        return ret;
    }

    /** Function which call the createComputer to create a computer in the database
     * with a SQL request.
     * @param computer computer to add to the database */
    public void setCompany(String name) throws Exception {
        this.companyDAOImpl.createCompany(new Company(name));
    }

    public void deleteCompany(long id) {
        companyDAOImpl.deleteCompany(id);
    }

}
