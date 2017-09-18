package test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.TestCase;
import main.model.Computer;
import main.service.ServiceComputer;

@RunWith(MockitoJUnitRunner.class)
public class ServiceComputerTest extends TestCase {
    @Mock
    private Computer computer;
    private static ArrayList<Computer> computers = new ArrayList<Computer>();
    private static ServiceComputer serviceComputer = new ServiceComputer();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Override
    @Before
    public void setUp() throws IOException {
    }

    @Override
    @After
    public void tearDown() throws IOException {

    }

    /** Test the created function for computer.*/
    @Test
    public void testCreateComputer() {

        Mockito.when(computer.getId()).thenReturn(1L);
        Mockito.when(computer.getName()).thenReturn("computer_test");
        Mockito.when(computer.getIntroduced()).thenReturn(Timestamp.valueOf("1991-01-01 00:00:00"));
        Mockito.when(computer.getDiscontinued()).thenReturn(Timestamp.valueOf("1991-01-01 00:00:00"));
        Mockito.when(computer.getCompanyId()).thenReturn(1L);

        computers = serviceComputer.setComputer(computer, computers);
        Computer computer = null;
        for (Computer c : computers) {
            if (c.getId() == 1L) {
                computer = c;
                break;
            }
        }
        assertEquals(computer.getId(), 1L);
        assertEquals(computer.getName(), "computer_test");
        assertEquals(computer.getIntroduced(), Timestamp.valueOf("1991-01-01 00:00:00"));
        assertEquals(computer.getDiscontinued(), Timestamp.valueOf("1991-01-01 00:00:00"));
        assertEquals(computer.getCompanyId(), 1L);
    }

}
