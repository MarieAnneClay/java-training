package persistence.dao;

import java.util.ArrayList;

import model.Company;
import persistence.daoUtil.DAOException;

public interface CompanyDAO {
    /** function to get all the companies in the database.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @return ArrayList of Company */
    ArrayList<Company> findAllCompanies() throws DAOException;

    /** function to get the company with the id.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @param id the id of the company search in the database
     * @return A Company */
    Company findByIdCompany(long id) throws DAOException;

    void createCompany(Company company) throws IllegalArgumentException, DAOException;

    /** function wich call a function in CompanyDAO to execute a sql requete to
     * delete a company in the database and the link with his computers.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @param id the id of the company to delete in the database */
    void deleteCompany(long id) throws DAOException;
}
