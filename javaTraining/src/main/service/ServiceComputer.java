package main.service;

import java.util.ArrayList;

import main.dao.dao.ComputerDAO;
import main.dao.daoUtil.ConnectionManager;
import main.model.Computer;

/** Class of service for Computer DAO. */
public class ServiceComputer {
	private ComputerDAO computerDAO;
	
	/** Constructor that instance the connection to the database for Computer DAO. */
	public ServiceComputer() {		
		this.computerDAO = ConnectionManager.getInstance().getComputerDao();
	}
	
	/** Function to get all the data from the table computer. 
	 * @return A list of all the Computer in the database
	 */
	public ArrayList<Computer> getAllComputers() {
  		return computerDAO.findAllComputers();
  	}
  	
	/** Function to get the computer. 
	 * @param id 
	 * @return A list of all the Computer in the database
	 */
	public Computer getComputer (long id) {
  		return this.computerDAO.findByIdComputer(id);
  	}
  	
  	public ArrayList<Computer> getComputerSubest (int start, int size, ArrayList<Computer> computers) {
  		ArrayList<Computer> ret = new ArrayList<Computer>();
		for (int i = start ; i < (start+size) ; i++) {
			if ( i >= computers.size() ) {
				break;
			}
			ret.add ( computers.get(i) );
		}
		return ret;
	}
  	
  	public ArrayList<Computer> setComputer (Computer computer, ArrayList<Computer> computers) {
  		this.computerDAO.createComputer(computer);
  		computers.add(computer);
  		return computers;
  	}
  	
  	public void updateComputer (Computer computer) {
  		this.computerDAO.updateComputer(computer);
  	}
  	
  	public ArrayList<Computer> deleteComputer (long id, ArrayList<Computer> computers) {
  		this.computerDAO.deleteComputer(id);
  		for (Computer computer : computers) {
  			if ( computer.getId() == id ) {
  				computers.remove(computer);
  				break;
  			}
  		}
  		return computers;
  	}	

}
