package persistence.daoImpl;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Company;
import persistence.dao.CompanyDAO;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private SessionFactory sessionFactory;
    private Session session = sessionFactory.getCurrentSession();
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";

    @Autowired
    public CompanyDAOImpl(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Company> findAllCompanies() {
        return (ArrayList<Company>) session.createQuery(SQL_SELECT_ALL).list();
    }

    @Override
    public Company findByIdCompany(Long id) {
        return (Company) session.load(Company.class, new Long(id));
    }

}