package service;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Computer;
import persistence.daoImpl.ComputerDAOImpl;
import util.Validator;

/** Class of service for Computer DAO. */
public class ServiceComputer {
    private ComputerDAOImpl computerDAOImpl;

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
    public void setComputer(String name, String introduced, String discontinued, String companyId) throws Exception {
        try {
            Validator.validationName(name);
        } catch (Exception e) {
            throw e;
        }

        try {
            Validator.validationDate(introduced);
        } catch (Exception e) {
            throw e;
        }
        try {
            Validator.validationDate(discontinued);
        } catch (Exception e) {
            throw e;
        }
        LocalDate introducedDate = introduced.equals("") ? null : LocalDate.parse(introduced);
        LocalDate discontinuedDate = discontinued.equals("") ? null : LocalDate.parse(discontinued);
        try {
            Validator.validationIntroducedBeforeDiscontinued(introducedDate, discontinuedDate);
        } catch (Exception e) {
            throw e;
        }
        this.computerDAOImpl.createComputer(new Computer(name, introducedDate, discontinuedDate, companyId == "" ? 0 : Integer.parseInt(companyId)));
    }

    /** Function which call the updateComputer to update a computer in the database
     * with a SQL request.
     * @param computer computer to update to the database */
    public void updateComputer(long id, String name, String introduced, String discontinued, String companyId) throws Exception {
        try {
            Validator.validationName(name);
        } catch (Exception e) {
            throw e;
        }

        try {
            Validator.validationDate(introduced);
        } catch (Exception e) {
            throw e;
        }
        try {
            Validator.validationDate(discontinued);
        } catch (Exception e) {
            throw e;
        }
        LocalDate introducedDate = introduced.equals("") ? null : LocalDate.parse(introduced);
        LocalDate discontinuedDate = discontinued.equals("") ? null : LocalDate.parse(discontinued);
        try {
            Validator.validationIntroducedBeforeDiscontinued(introducedDate, discontinuedDate);
        } catch (Exception e) {
            throw e;
        }
        Computer computer = this.getComputer(id);
        computer.setName(name);
        computer.setIntroduced(introducedDate);
        computer.setDiscontinued(discontinuedDate);
        computer.setCompanyId((companyId == "" ? 0 : Integer.parseInt(companyId)));

        this.computerDAOImpl.updateComputer(computer);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    public void deleteComputer(long id) {
        this.computerDAOImpl.deleteComputer(id);
    }

}
