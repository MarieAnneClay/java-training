package persistence;

import java.util.Vector;

import model.Company;

public interface CompanyDAO {
	void creer(Company company);
    Vector<Company> trouver(String sql, Object... objets);

}
