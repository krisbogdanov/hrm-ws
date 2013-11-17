package ws.services;

import ws.services.Impl.AddEmployee;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

public class AddEmployeeTests extends TestCase {
	
	
	private IAddEmployee addEmpl = new AddEmployee();
	private String empName = "Test";
	private String empSurname = "Test";
	private String empEmail = "test.test@hrm.com";
	private String empAddress = "3 Victoria Street, London";
	private String empSSN = "123-123-123";
	private String empPhone = "07654167165";
	/**
	 * This test should fail if the test is run more than once.
	 * Meaning that the test event will already be added so only the second
	 * test will succeed unless the event is deleted from the database.
	 */
	public void testAddEmployeeEqualsSuccess() {
	  String result = addEmpl.addEmployee(empName, empSurname, empEmail,
			  empAddress, empSSN, empPhone);
	  assertEquals(HRConstants.INSERT_SUCCESS, result);
	}
	public void testAddEmployeeEqualsAlreadyAdded() {
	  String result = addEmpl.addEmployee(empName, empSurname, empEmail,
				  empAddress, empSSN, empPhone);
	  assertEquals(HRConstants.EMPLOYEE_ALREADY_ADDED, result);
	}
	
}
