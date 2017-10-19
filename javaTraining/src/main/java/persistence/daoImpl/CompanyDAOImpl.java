package persistence.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Company;
import persistence.dao.CompanyDAO;
import persistence.daoUtil.DAOException;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private NamedParameterJdbcTemplate jdbc;
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";

    @Autowired
    public CompanyDAOImpl(NamedParameterJdbcTemplate jdbc) {
        super();
        this.jdbc = jdbc;
    }

    @Override
    public ArrayList<Company> findAllCompanies() {
        return (ArrayList<Company>) jdbc.query(SQL_SELECT_ALL, new CompanyMapper());
    }

    @Override
    public Company findByIdCompany(Long id) throws DAOException {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return jdbc.queryForObject(SQL_SELECT_BY_ID, paramSource, new CompanyMapper());
    }

    public class CompanyMapper implements RowMapper<Company> {
        @Override
        public Company mapRow(ResultSet resultSet, int rownumber) throws SQLException {
            Company company = new Company();
            company.setId(resultSet.getLong("id"));
            company.setName(resultSet.getString("name"));
            return company;
        }
    }

}