package service;

import java.util.ArrayList;

import DTO.ComputerDTO;
import DTO.ComputerMapper;
import model.Computer;
import persistence.daoImpl.CompanyDAOImpl;
import persistence.daoImpl.ComputerDAOImpl;
import persistence.daoUtil.ConnectionManager;
import util.Validator;
import util.ValidatorException;

/** Class of service for Computer DAO. */
public class ServiceComputer {
    private ComputerDAOImpl computerDAOImpl;
    private static final ServiceComputer INSTANCE = new ServiceComputer();

    public static ServiceComputer getInstance() {
        return INSTANCE;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
    }

    public void setCompanyDAOImpl(CompanyDAOImpl companyDAOImpl) {
    }

    public void setComputerDAOImpl(ComputerDAOImpl computerDAOImpl) {
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
    public void setComputer(ComputerDTO computerDTO) throws ValidatorException {
        Computer computer = ComputerMapper.convertDTOToComputer(computerDTO);
        Validator.validationComputer(computer.getName(), computer.getIntroduced(), computer.getDiscontinued());
        computerDAOImpl.createComputer(computer);
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

        computerDAOImpl.updateComputer(computer);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    public void deleteComputer(String ids) {
        for (String id : ids.split(",")) {
            computerDAOImpl.deleteComputer(Long.parseLong(id));
        }
    }

}
