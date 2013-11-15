package ws.services;

import ws.services.Impl.AddEmployee;
import junit.framework.TestCase;

public class AddEmployeeTests extends TestCase {
	
	private IAddEmployee addEmpl = new AddEmployee();
	private String empName = "Test";
	private String empSurname = "Test";
	private String empEmail = "test.test@hrm.com";
	private String empAddress = "3 Victoria Street, London";
	private String empSSN = "123-123-123";
	private String empPhone = "07654167165";
	
	public void testAddEmployeeEqualsSuccess() {
	  String result = addEmpl.addEmployee(empName, empSurname, empEmail,
			  empAddress, empSSN, empPhone);
	  assertEquals("Success", result);
	}
	
}
