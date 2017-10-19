package persistence.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import model.Computer;
import persistence.dao.ComputerDAO;
import persistence.daoUtil.DAOException;

@Component
public class ComputerDAOImpl implements ComputerDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private NamedParameterJdbcTemplate jdbc;
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM computer WHERE id = :id";
    private static final String SQL_SELECT_BY_NAME_AND_COMPANY = "SELECT * FROM computer cr LEFT JOIN company cy ON cr.company_id = cy.id WHERE cr.name LIKE '% :name %' OR cy.name LIKE '% :name %' ORDER BY :sort :order LIMIT :begin, :end";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM computer cr LEFT JOIN company cy ON cr.company_id = cy.id WHERE cr.name LIKE '% :name %' OR cy.name LIKE '% :name %' ";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( :name, :introduced, :discontinued, :companyId)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :companyId WHERE id = :id";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id = :id";

    @Autowired
    public ComputerDAOImpl(NamedParameterJdbcTemplate jdbc) {
        super();
        this.jdbc = jdbc;
    }

    @Override
    public Computer findByIdComputer(Long id) throws DAOException {
        // SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        // Map<String, Object> parameters = new HashMap<String, Object>();
        // parameters.put("id", id);
        // return (Computer) jdbcTemplate.queryForList(SQL_SELECT_BY_ID, parameters, new
        // ComputerMapper());
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return jdbc.queryForObject(SQL_SELECT_BY_ID, paramSource, new ComputerMapper());
    }

    @Override
    public ArrayList<Computer> findComputerByNameAndCompany(String name, int numberOfComputerByPage, int currentPage, String sort, String order) throws DAOException {
        int beginIndex = currentPage * numberOfComputerByPage;
        int endIndex = numberOfComputerByPage;
        /* MapSqlParameterSource namedParameters = new MapSqlParameterSource("name",
         * name); namedParameters.addValue("name", name); */
        // Map<String, Object> parameters = new HashMap<String, Object>();
        // parameters.put("name", name);
        // parameters.put("name", name);
        // parameters.put("sort", sort);
        // parameters.put("order", order);
        // parameters.put("beginIndex", beginIndex);
        // parameters.put("endIndex", endIndex);
        //
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("name", name);
        paramSource.addValue("name", name);
        paramSource.addValue("sort", sort);
        paramSource.addValue("order", order);
        paramSource.addValue("begin", beginIndex);
        paramSource.addValue("end", endIndex);

        return (ArrayList<Computer>) jdbc.query(SQL_SELECT_BY_NAME_AND_COMPANY, paramSource, new ComputerMapper());

        // return (ArrayList<Computer>)
        // jdbcTemplate.query(SQL_SELECT_BY_NAME_AND_COMPANY, parameters, new
        // ComputerMapper());
    }

    @Override
    public int getCount(String name) {
        /* MapSqlParameterSource namedParameters = new MapSqlParameterSource("name",
         * name); namedParameters.addValue("name", name); PreparedStatementSetter
         * preparedStatementSetter; PreparedStatement preparedStatement;
         * preparedStatement.setString("name", name);
         * preparedStatementSetter.setValues(preparedStatement); */
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("name", name);

        return jdbc.queryForList(SQL_COUNT, paramSource, Integer.class).get(0);
    }

    @Override
    public void createComputer(Computer computer) throws IllegalArgumentException, DAOException {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("name", computer.getName());
        paramSource.addValue("introduced", computer.getIntroduced());
        paramSource.addValue("discontinued", computer.getDiscontinued());
        paramSource.addValue("companyId", computer.getCompanyId());
        jdbc.update(SQL_INSERT, paramSource);

    }

    @Override
    public void updateComputer(Computer computer) throws DAOException {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("name", computer.getName());
        paramSource.addValue("introduced", computer.getIntroduced());
        paramSource.addValue("discontinued", computer.getDiscontinued());
        paramSource.addValue("companyId", computer.getCompanyId());
        paramSource.addValue("id", computer.getId());
        jdbc.update(SQL_UPDATE, paramSource);
    }

    @Override
    public void deleteComputer(Long id) throws DAOException {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        jdbc.update(SQL_DELETE, paramSource);
    }

    /** Mapper function for computerDAO.
     * @throws SQLException if the resultSet of the query as an exception
     * @param resultSet result of the query select
     * @return return the computer mapped */
    public class ComputerMapper implements RowMapper<Computer> {
        @Override
        public Computer mapRow(ResultSet resultSet, int rownumber) throws SQLException {
            Computer computer = new Computer();
            computer.setId(resultSet.getLong("id"));
            computer.setName(resultSet.getString("name"));
            computer.setIntroduced(resultSet.getDate("introduced") == null ? null : resultSet.getDate("introduced").toLocalDate());
            computer.setDiscontinued(resultSet.getDate("discontinued") == null ? null : resultSet.getDate("discontinued").toLocalDate());
            computer.setCompanyId(resultSet.getLong("company_id"));
            return computer;
        }
    }

}
