package ws.services;

import org.junit.Test;

import ws.services.Impl.AddEmployee;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class AddEmployeeTests extends TestCase {
	private IAddEmployee addEmployee = mock(AddEmployee.class);
	private String empName = "Test";
	private String empSurname = "Test";
	private String empEmail = "test.test@hrm.com";
	private String empAddress = "3 Victoria Street, London";
	private String empSSN = "123-123-123";
	private String empPhone = "07654167165";
	private int empType = 1;
	private String empUsername = "Test";
	private String empPassword = "Test";
	@Test
	public void testAddEmployeeSuccess() {
		when(addEmployee.addEmployee(empName, empSurname, empEmail,
			  empAddress, empSSN, empPhone, empType, empUsername, empPassword)).
			 thenReturn(HRConstants.INSERT_SUCCESS);
	}
	@Test
	public void testAddEmployeeAlreadyAdded() {
		when(addEmployee.addEmployee(empName, empSurname, empEmail,
			  empAddress, empSSN, empPhone, empType, empUsername, empPassword)).
			 thenReturn(HRConstants.EMPLOYEE_ALREADY_ADDED);
	}
	
	
}
