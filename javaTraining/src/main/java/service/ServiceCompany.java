package service;

import java.util.ArrayList;

import dao.dao.CompanyDAO;
import dao.daoUtil.ConnectionManager;
import model.Company;

public class ServiceCompany {
    private CompanyDAO companyDAO;

    /** CONSTRUCTOR. */
    public ServiceCompany() {
        this.companyDAO = ConnectionManager.getInstance().getCompanyDao();
    }

    public ArrayList<Company> getAllCompanies() {
        return companyDAO.findAllCompanies();
    }

    /**
     * Function search company by id in the database.
     * 
     * @param id
     *            id of the searched company
     * @return company searched
     */
    public Company getCompany(long id) {
        return this.companyDAO.findByIdCompany(id);
    }

    /**
     * Function to have a certain proportion of the company list.
     * 
     * @param start
     *            start of the list
     * @param size
     *            size of the list
     * @param companies
     *            list of all companies
     * @return ArrayList of the subsided company
     */
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

}
