package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import javax.activation.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import main.dao.daoImpl.CompanyDAOImpl;
import main.model.Computer;

@RunWith(MockitoJUnitRunner.class)
/** Test class for ServiceComputer. */
public final class ComputerDaoImplTest {
  @Mock
  private DataSource ds;

  @Mock
  private Connection c;

  @Mock
  private PreparedStatement stmt;

  @Mock
  private ResultSet rs;

  Computer computer = Mockito.mock(Computer.class);

  @Before
  public void setUp() throws Exception {
    /*
     * assertNotNull(ds);
     * 
     * when(c.prepareStatement(any(String.class))).thenReturn(stmt);
     * 
     * when(((StatementImpl) ds).getConnection()).thenReturn(c);
     * 
     * computer = new Computer();
     * 
     * computer.setId(1); computer.setName("Test");
     * computer.setIntroduced(LocalDate.of(2013, 4, 12));
     * computer.setDiscontinued(LocalDate.of(2013, 4, 12));
     * computer.setCompanyId(1);
     * 
     * when(rs.first()).thenReturn(true);
     * 
     * when(rs.getInt(1)).thenReturn(1);
     * 
     * when(rs.getString(2)).thenReturn(computer.getFirstName());
     * 
     * when(rs.getString(3)).thenReturn(computer.getLastName());
     * 
     * when(stmt.executeQuery()).thenReturn(rs);
     */
    MockitoAnnotations.initMocks(this);

  }

  @Test(expected = IllegalArgumentException.class)
  public void nullCreateThrowsException() {

    new CompanyDAOImpl(ds).create(null);

  }

  @Test
  public void createPerson() {

    new ComputerDaoImpl(ds).create(p);

  }

  @Test
  public void createAndRetrievePerson() throws Exception {

    ComputerDaoImpl dao = new ComputerDaoImpl(ds);

    dao.create(p);

    Computer r = dao.retrieve(1);

    assertEquals(computer, r);

  }
}
