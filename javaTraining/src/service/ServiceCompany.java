package service;

import java.util.LinkedList;

import dao.CompanyDAO;
import model.Company;

public class ServiceCompany {
	private LinkedList<Company> companies;
	private CompanyDAO companyDAO;
	
	public ServiceCompany(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
		this.companies = companyDAO.trouver("SELECT * FROM company WHERE 1 = ?", "1");
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
