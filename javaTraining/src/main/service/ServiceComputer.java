package main.service;

import java.util.ArrayList;

import main.dao.dao.ComputerDAO;
import main.dao.daoUtil.ConnectionManager;
import main.model.Computer;

/** Class of service for Computer DAO. */
public class ServiceComputer {
    private ComputerDAO computerDAO;

    /**
     * Constructor that instance the connection to the database for Computer DAO.
     */
    public ServiceComputer() {
        this.computerDAO = ConnectionManager.getInstance().getComputerDao();
    }

    /**
     * Function to get all the data from the table computer.
     * @return ArrayList of computer list of all the Computer in the database
     */
    public ArrayList<Computer> getAllComputers() {
        return computerDAO.findAllComputers();
    }

    /** Function to get the computer.
     * @param id of the computer searched
     * @return A list of all the Computer in the database
     */
    public Computer getComputer(long id) {
        return this.computerDAO.findByIdComputer(id);
    }

    /** Function to have a certain proportion of the computer list.
     * @param start start of the list
     * @param size size of the list
     * @param computers list of all computers
     * @return ArrayList of the subsided Computer*/
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

    /** Function wich call the createComputer to create a computer in the database with a SQL request.
     * @param computer computer to add to the database
     * @param computers the list of the current computers
     * @return ArrayList Computer list with the computer added*/
    public ArrayList<Computer> setComputer(Computer computer, ArrayList<Computer> computers) {
        this.computerDAO.createComputer(computer);
        computers.add(computer);
        return computers;
    }

    /** Function wich call the updateComputer to update a computer in the database with a SQL request.
     * @param computer computer to update to the database*/
    public void updateComputer(Computer computer) {
        this.computerDAO.updateComputer(computer);
    }

    /** Function wich call the deleteComputer to delete a computer in the database with a SQL request.
     * @param id id of the computer to delete in the database
     * @param computers the list of the current computers
     * @return ArrayList Computer list with the computer deleted*/
    public ArrayList<Computer> deleteComputer(long id, ArrayList<Computer> computers) {
        this.computerDAO.deleteComputer(id);
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                computers.remove(computer);
                break;
            }
        }
        return computers;
    }

}
