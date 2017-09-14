package main.dao.dao;

import java.util.ArrayList;

import main.dao.daoUtil.DAOException;
import main.model.Computer;

public interface ComputerDAO {
	public ArrayList<Computer> findAllComputers () throws DAOException;
	public Computer findByIdComputer (long id) throws DAOException;	
	public void createComputer (Computer computer) throws DAOException;
	public void updateComputer (Computer computer) throws DAOException;
	public void deleteComputer (long id) throws DAOException;
}
