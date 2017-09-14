package main.dao.dao;

import java.util.ArrayList;

import main.dao.daoUtil.DAOException;
import main.model.Company;

public interface CompanyDAO {
	public ArrayList<Company> findAllCompanies () throws DAOException;
	public Company findByIdCompany(long id) throws DAOException;
}
