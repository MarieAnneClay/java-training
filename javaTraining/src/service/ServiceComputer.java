package service;

import java.util.LinkedList;

import dao.ComputerDAO;
import model.Computer;

public class ServiceComputer {
	private LinkedList<Computer> computers = new LinkedList<Computer>();
	private ComputerDAO computerDAO;
	
	public ServiceComputer(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
		this.computers = computerDAO.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
	}
	
	public LinkedList<Computer> getComputers(){
  		return this.computers;
  	}
  	
  	public Computer getComputer(long id){
  		Computer computer = this.computerDAO.trouver("SELECT * FROM computer WHERE id = ?", id).get(0);  		
  		return computer;
  	}
  	
  	public void setComputers(LinkedList<Computer> computers) {
  		this.computers = computers;
  	}
  	
  	public LinkedList<Computer> getComputerSubest(int start, int size) {
  		LinkedList<Computer> ret = new LinkedList<Computer>();
		for (int i = start ; i < (start+size) ; i++) {
			if ( i >= this.computers.size() ) {
				break;
			}
			ret.add(this.computers.get(i));
		}
		return ret;
	}
  	
  	public void createComputer(Computer computer) {
  		this.computerDAO.creer(computer);
  		this.computers.removeAll(this.computers);
  		this.computers = this.computerDAO.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
  	}
  	
  	public void updateComputer(Computer computer) {
  		this.computerDAO.modifier(computer);
  		this.computers.removeAll(this.computers);
  		this.computers = this.computerDAO.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
  	}
  	
  	public void deleteComputer(Computer computer) {
  		this.computerDAO.supprimer(computer);
  		this.computers.removeAll(this.computers);
  		this.computers = this.computerDAO.trouver("SELECT * FROM computer WHERE 1 = ?", "1");
  	}	

}
