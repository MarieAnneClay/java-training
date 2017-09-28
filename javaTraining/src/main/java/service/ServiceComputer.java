package service;

import java.util.ArrayList;

import DTO.ComputerDTO;
import DTO.ComputerMapper;
import model.Computer;
import persistence.daoImpl.ComputerDAOImpl;
import util.Validator;
import util.ValidatorException;

/** Class of service for Computer DAO. */
public class ServiceComputer {
    private ComputerDAOImpl computerDAOImpl;
    private static final ServiceComputer INSTANCE = new ServiceComputer();

    public static ServiceComputer getInstance() {
        return INSTANCE;
    }

    /** Constructor that instance the connection to the database for Computer
     * DAO. */
    public ServiceComputer() {
        this.computerDAOImpl = new ComputerDAOImpl();
    }

    /** Function to get all the data from the table computer.
     * @return ArrayList of computer list of all the Computer in the database */
    public ArrayList<Computer> getAllComputers() {
        return computerDAOImpl.findAllComputers();
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
    public ArrayList<Computer> getComputerByName(String name) {
        return this.computerDAOImpl.findComputerByNameAndCompany(name);
    }

    /** Function to have a certain proportion of the computer list.
     * @param start start of the list
     * @param size size of the list
     * @param computers list of all computers
     * @return ArrayList of the subsided Computer */
    public ArrayList<Computer> getComputerSubest(int start, int size, ArrayList<Computer> computers) {
        ArrayList<Computer> ret = new ArrayList<Computer>();
        for (int i = start; i < (start + size); i++) {
            if (i >= computers.size()) {
                break;
            }
            ret.add(computers.get(i));
        }
        return ret;
    }

    /** Function which call the createComputer to create a computer in the database
     * with a SQL request.
     * @param computer computer to add to the database */
    public void setComputer(ComputerDTO computerDTO) throws ValidatorException {
        Computer computer = ComputerMapper.convertDTOToComputer(computerDTO);
        Validator.validationComputer(computer.getName(), computer.getIntroduced(), computer.getDiscontinued());
        this.computerDAOImpl.createComputer(computer);
    }

    /** Function which call the updateComputer to update a computer in the database
     * with a SQL request.
     * @param computer computer to update to the database */
    public void updateComputer(ComputerDTO computerDTO) throws ValidatorException {
        Computer computerMapper = ComputerMapper.convertDTOToComputer(computerDTO);
        Validator.validationComputer(computerMapper.getName(), computerMapper.getIntroduced(), computerMapper.getDiscontinued());

        Computer computer = this.getComputer(computerMapper.getId());
        computer.setName(computerMapper.getName());
        computer.setIntroduced(computerMapper.getIntroduced());
        computer.setDiscontinued(computerMapper.getDiscontinued());
        computer.setCompanyId(computerMapper.getCompanyId());

        this.computerDAOImpl.updateComputer(computer);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    public void deleteComputer(String ids) {
        for (String id : ids.split(",")) {
            this.computerDAOImpl.deleteComputer(Integer.parseInt(id));
        }
    }

}
