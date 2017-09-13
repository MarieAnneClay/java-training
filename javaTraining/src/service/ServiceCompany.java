package service;

import java.util.ArrayList;

import dao.CompanyDAO;
import model.Company;

public class ServiceCompany {
	private CompanyDAO companyDAO;
	
	public ArrayList<Company> ServiceCompany(CompanyDAO companyDAO) {
		return companyDAO.trouver("SELECT * FROM company WHERE 1 = ?", "1");
	}
	
	public LinkedList<Company> getCompanies(){
   		return companies;
   	}
   	
   	public Company getCompany(long id){
   		Company company = companyDAO.trouver("SELECT * FROM company WHERE id = ?", id).get(0);  		
  		return company;
  	}
   	
   	public void setCompanies(LinkedList<Company> companies) {
   		this.companies = companies;
   	}
   	
   	public LinkedList<Company> getCompanySubest(int start, int size) {
   		LinkedList<Company> ret = new LinkedList<Company>();
		for (int i = start ; i < (start+size) ; i++) {
			if ( i >= companies.size() ) {
				break;
			}
			ret.add(companies.get(i));
		}
		return ret;
	}

}
