import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.TestCase;
import model.Company;
import model.Computer;
import persistence.daoImpl.CompanyDAOImpl;
import service.ServiceCompany;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCompanyTest extends TestCase {
    @Mock
    CompanyDAOImpl companyDAOImpl;

    @InjectMocks
    ServiceCompany serviceCompany = new ServiceCompany();

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

    @Test
    public void testDeleteCompany() {
        Company company = new Company(1L);
        Company company2 = new Company(1L);

        Mockito.when(companyDAOImpl.findByIdCompany(1L)).thenReturn(company2);

        serviceCompany.deleteCompany(company.getId());

        Mockito.verify(companyDAOImpl, Mockito.times(1)).deleteCompany(company2.getId());

    }

    @Test
    public void testDeleteCompanyWithComputers() {
        Company company = new Company(1L);
        Company company2 = new Company(1L);
        Computer computer = new Computer(1L);
        computer.setCompanyId(company.getId());

        Mockito.when(companyDAOImpl.findByIdCompany(1L)).thenReturn(company2);
        serviceCompany.deleteCompany(company.getId());

        Mockito.verify(companyDAOImpl, Mockito.times(1)).deleteCompany(company2.getId());
        assertEquals(computer.getCompanyId(), null);
    }

    @Test(expected = Exception.class)
    public void testDeleteCompanyWithRollback() {
        Company company = new Company(1L);
        // Mockito.doThrow(Exception.class).when(serviceCompany).deleteCompany(company.getId());
        serviceCompany.deleteCompany(company.getId());
        assertEquals(company.getId(), 1L);
    }

}
