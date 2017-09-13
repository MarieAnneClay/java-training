package dao;

import java.util.ArrayList;

import daoUtil.DAOException;
import model.Company;

public interface CompanyDAO {
	public ArrayList<Company> findAllCompanies () throws DAOException;
	public Company findByIdCompany (int id) throws DAOException;
}
