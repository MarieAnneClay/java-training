package persistence.daoImpl;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Company;
import persistence.dao.CompanyDAO;
import persistence.daoUtil.DAOException;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<Company> findAllCompanies() {
        return (ArrayList<Company>) jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper(Company.class));
    }

    @Override
    public Company findByIdCompany(Long id) throws DAOException {
        return (Company) jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[] { id }, new BeanPropertyRowMapper(Company.class));
    }
}