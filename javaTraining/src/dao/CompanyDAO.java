package dao;

import java.util.LinkedList;
import java.util.Vector;

import model.Company;

public interface CompanyDAO {
	void creer(Company company);
    LinkedList<Company> trouver(String sql, Object... objets);

}
