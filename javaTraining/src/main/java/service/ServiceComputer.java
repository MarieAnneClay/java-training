package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Computer;
import persistence.daoImpl.ComputerDAOImpl;
import util.ValidatorException;

/** Class of service for Computer DAO. */
@Service
public class ServiceComputer {
    private final ComputerDAOImpl computerDAOImpl;

    @Autowired
    public ServiceComputer(ComputerDAOImpl computerDAOImpl) {
        super();
        this.computerDAOImpl = computerDAOImpl;
    }

    /** Function to get the computer.
     * @param id of the computer searched
     * @return A list of all the Computer in the database */
    public Computer getComputer(Long id) {
        return computerDAOImpl.findByIdComputer(id);
    }

    /** Function to get the computer.
     * @param name of the computer searched
     * @return A list of all the Computer in the database */
    public ArrayList<Computer> getComputerByName(String name, int numberOfComputerByPage, int currentPage, String sort, String order) {
        return computerDAOImpl.findComputerByNameAndCompany(name, numberOfComputerByPage, currentPage, sort, order);
    }

    public int getCount(String name) {
        return computerDAOImpl.getCount(name);
    }

    /** Function which call the createComputer to create a computer in the database
     * with a SQL request.
     * @param computer computer to add to the database */
    public void setComputer(Computer result) throws ValidatorException {
        computerDAOImpl.createComputer(result);
    }

    /** Function which call the updateComputer to update a computer in the database
     * with a SQL request.
     * @param computer computer to update to the database */
    public void updateComputer(Computer result) throws ValidatorException {

        Computer computer = this.getComputer(result.getId());
        computer.setName(result.getName());
        computer.setIntroduced(result.getIntroduced());
        computer.setDiscontinued(result.getDiscontinued());
        computer.setCompanyId(result.getCompanyId());

        computerDAOImpl.updateComputer(computer);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    public void deleteComputer(ArrayList<Long> ids) {
        for (Long id : ids) {
            computerDAOImpl.deleteComputer(id);
        }
    }

}
