package persistence.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import model.Computer;
import persistence.dao.ComputerDAO;
import persistence.daoUtil.DAOException;

@Component
public class ComputerDAOImpl implements ComputerDAO {

    private static Logger LOGGER = Logger.getLogger(ComputerDAOImpl.class.getName());
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME_AND_COMPANY = "SELECT * FROM computer cr LEFT JOIN company cy ON cr.company_id = cy.id WHERE cr.name LIKE CONCAT('%', ? , '%') OR cy.name LIKE CONCAT('%', ? , '%') ORDER BY ? ? LIMIT ?,?";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM computer cr " + "LEFT JOIN company cy ON cr.company_id = cy.id " + "WHERE cr.name LIKE CONCAT('%', ? , '%') "
            + "OR cy.name LIKE CONCAT('%', ? , '%') ";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ('?', ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = '?', introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Computer findByIdComputer(Long id) throws DAOException {
        return (Computer) jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[] { id }, new BeanPropertyRowMapper(Computer.class));
    }

    @Override
    public ArrayList<Computer> findComputerByNameAndCompany(String name, int numberOfComputerByPage, int currentPage, String sort, String order) throws DAOException {
        int beginIndex = currentPage * numberOfComputerByPage;
        int endIndex = numberOfComputerByPage;
        ArrayList<Computer> computers;
        List<Map> rows = jdbcTemplate.queryForList(SQL_SELECT_BY_NAME_AND_COMPANY, new Object[] { name, name, sort, order, beginIndex, endIndex });
        for (Map row : rows) {
            computers.add(mapComputer(row));
        }
        for (Computer computer : computers) {
            LOGGER.warning(computer.getName());
            LOGGER.warning(computer.getId().toString());
        }
        return computers;
    }

    @Override
    public int getCount(String name) {
        return jdbcTemplate.queryForObject(SQL_COUNT, new Object[] { name, name }, Integer.class);
    }

    @Override
    public void createComputer(Computer computer) throws IllegalArgumentException, DAOException {
        jdbcTemplate.update(SQL_INSERT, new Object[] { computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId() });

    }

    @Override
    public void updateComputer(Computer computer) throws DAOException {
        jdbcTemplate.update(SQL_UPDATE, new Object[] { computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId() });
    }

    @Override
    public void deleteComputer(Long id) throws DAOException {
        jdbcTemplate.update(SQL_DELETE, new Object[] { id });
    }

    /** Mapper function for computerDAO.
     * @throws SQLException if the resultSet of the query as an exception
     * @param resultSet result of the query select
     * @return return the computer mapped */
    public static Computer mapComputer(Map resultSet) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getLong("id"));
        computer.setName(resultSet.getString("name"));
        computer.setIntroduced(resultSet.getDate("introduced") == null ? null : resultSet.getDate("introduced").toLocalDate());
        computer.setDiscontinued(resultSet.getDate("discontinued") == null ? null : resultSet.getDate("discontinued").toLocalDate());
        computer.setCompanyId(resultSet.getLong("company_id"));
        return computer;
    }
}
