package service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Computer;
import persistence.ComputerRepository;
import util.ValidatorException;

/** Class of service for Computer DAO. */
@Service
@Transactional
public class ServiceComputer {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    private final ComputerRepository computerRepository;

    @Autowired
    public ServiceComputer(ComputerRepository computerRepository) {
        super();
        this.computerRepository = computerRepository;
    }

    public Long getCount(String name) {
        return computerRepository.countByNameContainingOrCompanyNameContaining(name, name);
    }

    /** Function to get the computer.
     * @param name of the computer searched
     * @return A list of all the Computer in the database */
    // public ArrayList<Computer> getComputerByName(String name, int
    // numberOfComputerByPage, int currentPage, String sort, String order) {
    public Page<Computer> getComputerByName(Pageable page, String name) {
        return computerRepository.findAllComputersByNameContainingOrCompanyNameContaining(page, name, name);
        // return computerRepository.findComputerByNameAndCompany(name,
        // numberOfComputerByPage, currentPage, sort, order);
    }

    public Computer getComputerById(Long id) {
        return computerRepository.findById(id).get();
    }

    /** Function which call the createComputer to create a computer in the database
     * with a SQL request.
     * @param computer computer to add to the database */
    public void setComputer(Computer result) throws ValidatorException {
        computerRepository.saveAndFlush(result);
    }

    /** Function which call the updateComputer to update a computer in the database
     * with a SQL request.
     * @param computer computer to update to the database */
    public void updateComputer(Computer result) throws ValidatorException {
        computerRepository.saveAndFlush(result);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    public void deleteComputer(String ids) {
        for (String id : ids.split(",")) {
            computerRepository.deleteById(Long.parseLong(id));
        }
    }

}
