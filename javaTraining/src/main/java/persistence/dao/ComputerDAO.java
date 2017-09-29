package persistence.dao;

import java.util.ArrayList;

import model.Computer;
import persistence.daoUtil.DAOException;

public interface ComputerDAO {
    /** function to get the company with the id.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @param id the id of the company search in the database
     * @return A Company */
    Computer findByIdComputer(long id) throws DAOException;

    /** function to get the company with the name.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @param name the name of the company search in the database
     * @return A Company */
    ArrayList<Computer> findComputerByNameAndCompany(String name, int numberOfComputerByPage, int currentPage, String sort, String order) throws DAOException;

    int getCount(String name);

    /** function wich call a function in ComputerDAO to execute a sql requete to
     * create a computer in the database.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @throws IllegalArgumentException if the argument have a problem
     * @param computer the computer to create in the database */
    void createComputer(Computer computer) throws IllegalArgumentException, DAOException;

    /** function wich call a function in ComputerDAO to execute a sql requete to
     * update a computer in the database.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @param computer the computer to update in the database */
    void updateComputer(Computer computer) throws DAOException;

    /** function wich call a function in ComputerDAO to execute a sql requete to
     * delete a computer in the database.
     * @throws DAOException a DAO exception (src/main/daoUtil)
     * @param id the id of the computer to delete in the database */
    void deleteComputer(long id) throws DAOException;

    void updateCompanyId(long id) throws DAOException;
}
