package service;

import java.util.ArrayList;

import dao.CompanyDAO;
import daoUtil.ConnectionManager;
import model.Company;

public class ServiceCompany {
private CompanyDAO companyDAO;
	
	public ServiceCompany () {		
		this.companyDAO = ConnectionManager.getInstance().getCompanyDao();
	}

	public ArrayList<Company> getAllCompanies () {
  		return companyDAO.findAllCompanies();
  	}
  	
  	public Company getCompany (long id) {
  		return this.companyDAO.findByIdCompany(id);
  	}
   	
   	public ArrayList<Company> getCompanySubest(int start, int size, ArrayList<Company> companies) {
   		ArrayList<Company> ret = new ArrayList<Company>();
		for (int i = start ; i < (start+size) ; i++) {
			if ( i >= companies.size() ) {
				break;
			}
			ret.add(companies.get(i));
		}
		return ret;
	}

}
