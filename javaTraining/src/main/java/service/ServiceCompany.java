package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Company;
import persistence.daoImpl.CompanyDAOImpl;
import persistence.daoImpl.ComputerDAOImpl;
import persistence.daoUtil.ConnectionManager;
import persistence.daoUtil.DAOException;
import persistence.daoUtil.TransactionManager;

public class ServiceCompany {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    private static CompanyDAOImpl companyDAOImpl = CompanyDAOImpl.getInstance();
    private static ComputerDAOImpl computerDAOImpl = ComputerDAOImpl.getInstance();
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
        companyDAOImpl.createCompany(new Company(name));
    }

    public void deleteCompany(long id) throws SQLException {
        Connection connexion = ConnectionManager.getInstance().getConnection();
        TransactionManager.setConnection(connexion);

        try {
            TransactionManager.setAutoCommit(false);
            computerDAOImpl.updateCompanyId(id);
            companyDAOImpl.deleteCompany(id);
            TransactionManager.commit();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            if (TransactionManager.getConnection() != null) {
                try {
                    TransactionManager.rollback();
                } catch (SQLException excep) {
                    LOGGER.log(Level.SEVERE, excep.getMessage(), excep);
                    throw new DAOException(excep);
                }
            }

        } finally {
            TransactionManager.remove();
        }

    }
}
