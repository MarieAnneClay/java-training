package persistence.daoImpl;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Computer;
import persistence.dao.ComputerDAO;

@Repository
public class ComputerDAOImpl implements ComputerDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public ComputerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Computer findByIdComputer(Long id) {
        return sessionFactory.getCurrentSession().load(Computer.class, new Long(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Computer> findComputerByNameAndCompany(String name, int numberOfComputerByPage, int currentPage, String sort, String order) {
        int beginIndex = currentPage * numberOfComputerByPage;
        int endIndex = numberOfComputerByPage;

        return (ArrayList<Computer>) sessionFactory.getCurrentSession().createCriteria(Computer.class, "computer").createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.or(Restrictions.like("computer.name", "%" + name + "%"), Restrictions.like("company.name", "%" + name + "%")))
                .addOrder(order.equals("ASC") ? Order.asc(sort) : Order.desc(sort)).setFirstResult(beginIndex).setMaxResults(endIndex);
    }

    @Override
    public Long getCount(String name) {
        sessionFactory.openSession();
        return (Long) sessionFactory.getCurrentSession().createCriteria(Computer.class, "computer").createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.or(Restrictions.like("computer.name", "%" + name + "%"), Restrictions.like("company.name", "%" + name + "%"))).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void createComputer(Computer computer) {
        sessionFactory.getCurrentSession().persist(computer);

    }

    @Override
    public void updateComputer(Computer computer) {
        sessionFactory.getCurrentSession().update(computer);
    }

    @Override
    public void deleteComputer(Long id) {
        Computer p = sessionFactory.getCurrentSession().load(Computer.class, new Long(id));
        if (null != p) {
            sessionFactory.getCurrentSession().delete(p);
        }
    }

}
