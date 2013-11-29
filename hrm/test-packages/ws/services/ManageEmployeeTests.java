package ws.services;

import org.junit.Test;

import ws.services.Impl.ManageEmployee;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class ManageEmployeeTests extends TestCase {
	private IManageEmployee manageEmployee = mock(ManageEmployee.class);
	private String token = "1981-124-asd-213";
	private String empName = "Test";
	private String empSurname = "Test";
	private String empEmail = "test.test@hrm.com";
	private String empAddress = "3 Victoria Street, London";
	private String empSSN = "123-123-123";
	private String empPhone = "07654167165";
	private int empType = 0;
	@Test
	public void testAddEmployeeSuccess() {
		when(manageEmployee.addEmployee(token, empName, empSurname, empEmail,
			  empAddress, empSSN, empPhone, empType)).
			  thenReturn(HRConstants.INSERT_SUCCESS);
		assertEquals(HRConstants.INSERT_SUCCESS, manageEmployee.addEmployee(token, empName, empSurname, empEmail,
			  empAddress, empSSN, empPhone, empType));
		verify(manageEmployee).addEmployee(token, empName, empSurname, empEmail,
				  empAddress, empSSN, empPhone, empType);
	}
	@Test
	public void testAddEmployeeAlreadyAdded() {
		when(manageEmployee.addEmployee(token, empName, empSurname, empEmail,
			  empAddress, empSSN, empPhone, empType)).
			  thenReturn(HRConstants.EMPLOYEE_ALREADY_ADDED);
		assertEquals(HRConstants.EMPLOYEE_ALREADY_ADDED, manageEmployee.addEmployee(token, empName, empSurname, empEmail,
				  empAddress, empSSN, empPhone, empType));
		verify(manageEmployee).addEmployee(token, empName, empSurname, empEmail,
				  empAddress, empSSN, empPhone, empType);	
	}
	@Test
	public void testRemoveEmployeeById() {
//		when(manageEmployee.removeEmployeeById(token, 1)).then
	}
	
}
