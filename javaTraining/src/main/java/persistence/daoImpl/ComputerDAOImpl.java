package persistence.daoImpl;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.hibernate.Session;
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
    private Session session = sessionFactory.getCurrentSession();

    @Autowired
    public ComputerDAOImpl(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Computer findByIdComputer(Long id) {
        return (Computer) session.load(Computer.class, new Long(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Computer> findComputerByNameAndCompany(String name, int numberOfComputerByPage, int currentPage, String sort, String order) {
        int beginIndex = currentPage * numberOfComputerByPage;
        int endIndex = numberOfComputerByPage;

        return (ArrayList<Computer>) session.createCriteria(Computer.class).createCriteria("computer", "company", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.or(Restrictions.like("computer.name", "%" + name + "%"), Restrictions.like("company.name", "%" + name + "%")))
                .addOrder(order.equals("ASC") ? Order.asc(sort) : Order.desc(sort)).setFirstResult(beginIndex).setMaxResults(endIndex);
    }

    @Override
    public int getCount(String name) {
        return (int) session.createCriteria(Computer.class).createCriteria("computer", "company", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.or(Restrictions.like("computer.name", "%" + name + "%"), Restrictions.like("company.name", "%" + name + "%"))).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void createComputer(Computer computer) {
        session.persist(computer);

    }

    @Override
    public void updateComputer(Computer computer) {
        session.update(computer);
    }

    @Override
    public void deleteComputer(Long id) {
        Computer p = (Computer) session.load(Computer.class, new Long(id));
        if (null != p) {
            session.delete(p);
        }
    }

}
