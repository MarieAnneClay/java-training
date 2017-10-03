package service;

import java.util.ArrayList;

import model.Computer;
import persistence.daoImpl.ComputerDAOImpl;
import util.Validator;
import util.ValidatorException;

/** Class of service for Computer DAO. */
public class ServiceComputer {
    private ComputerDAOImpl computerDAOImpl = ComputerDAOImpl.getInstance();
    private static final ServiceComputer INSTANCE = new ServiceComputer();

    public static ServiceComputer getInstance() {
        return INSTANCE;
    }

    /** Constructor that instance the connection to the database for Computer
     * DAO. */
    public ServiceComputer() {
        this.computerDAOImpl = new ComputerDAOImpl();
    }

    /** Function to get the computer.
     * @param id of the computer searched
     * @return A list of all the Computer in the database */
    public Computer getComputer(long id) {
        return this.computerDAOImpl.findByIdComputer(id);
    }

    /** Function to get the computer.
     * @param name of the computer searched
     * @return A list of all the Computer in the database */
    public ArrayList<Computer> getComputerByName(String name, int nb) {
        return this.computerDAOImpl.findComputerByNameAndCompany(name, nb);
    }

    public ArrayList<Computer> getComputer() {
        return this.computerDAOImpl.findComputer();
    }

    public int getCount(String name) {
        return this.computerDAOImpl.getCount(name);
    }

    /** Function which call the createComputer to create a computer in the database
     * with a SQL request.
     * @param computer computer to add to the database */
    public void setComputer(Computer computer) throws ValidatorException {
        Validator.validationComputer(computer.getName(), computer.getIntroduced(), computer.getDiscontinued());
        this.computerDAOImpl.createComputer(computer);
    }

    /** Function which call the updateComputer to update a computer in the database
     * with a SQL request.
     * @param computer computer to update to the database */
    public void updateComputer(Computer computer) throws ValidatorException {
        Validator.validationComputer(computer.getName(), computer.getIntroduced(), computer.getDiscontinued());

        Computer comp = this.getComputer(computer.getId());
        comp.setName(computer.getName());
        comp.setIntroduced(computer.getIntroduced());
        comp.setDiscontinued(computer.getDiscontinued());
        comp.setCompanyId(computer.getCompanyId());

        this.computerDAOImpl.updateComputer(comp);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    public void deleteComputer(String ids) {
        this.computerDAOImpl.deleteComputer(ids);
    }

}
