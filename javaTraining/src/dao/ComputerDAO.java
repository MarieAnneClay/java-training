package dao;

import java.util.ArrayList;

import daoUtil.DAOException;
import model.Computer;

public interface ComputerDAO {
	public ArrayList<Computer> findAllComputers () throws DAOException;
	public Computer findByIdComputer (int id) throws DAOException;	
	public void createComputer (Computer computer) throws DAOException;
	public void updateComputer (Computer computer) throws DAOException;
	public void deleteComputer (int id) throws DAOException;
}
