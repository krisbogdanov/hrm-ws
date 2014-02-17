package ws.services;


import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import ws.dao.BankDetails;
import ws.dao.Employee;
import ws.services.Impl.ManageEmployee;
import junit.framework.TestCase;

public class ManageEmployeeTests extends TestCase {
	
	private IManageEmployee manager = new ManageEmployee();
	private String token = "writeToken";
	@Test
	public void testAddEmployeeThenGetItByUsernameAndThenRemove() {
		int result = manager.addEmployee(token, "name", "sur",
				"email", "address", "ssn", "phone", 1, "role", "user", false);
		assertEquals(1, result);
		Employee emp = manager.getEmployeeByUsername(token, "user", false);
		assertNotNull(emp);
		int resultRemove = manager.removeEmployeeById(token, emp.getEmployeeId(), false);
		assertEquals(1, resultRemove);
	}
	@Test
	public void testEditEmployee() {
		int result = manager.editEmployeeById(token, 4, "Kris" , 
				"Bogdanov", "kris.b@hr.com", "320 plymouth grove", "SN-12-34-56-B", "0756145856182", 1, "Manager", "krisb", false);
		assertEquals(1, result);
	}
	
	@Test
	public void testGetEmployeeByIdAndThenChangePassword() {
		Employee emp = manager.getEmployeeById("readToken", 4, false);
		assertNotNull(emp);
		String randomPass = RandomStringUtils.randomAlphabetic(10);
		int result = manager.changeEmployeePassword("readToken", 4, emp.getEmployeePassword(), randomPass, false);
		assertEquals(1, result);
	}
	
	@Test
	public void testGetAllEmployees() {
		List<Employee> list = manager.getAllEmployees(token, false);
		assertNotNull(list);
	}
	
	@Test
	public void testGetBankDetailsByEmpId() {
		BankDetails details = manager.getBankDetailsByEmployeeId(token, 4, false);
		assertNotNull(details);
	}
	
	@Test
	public void testEditBankDetailsByEmpId() {
		int result = manager.editBankDetailsByEmployeeId(token, 4, "RBS", 124512541, 413512, false);
		assertEquals(1, result);
		int resultNegative = manager.editBankDetailsByEmployeeId("readToken", 6, "RBS", 124512541, 413512, false);
		assertEquals(0, resultNegative);
	}
	
	@Test
	public void testAddEmployeeThenBankDetailsThenRemoveBankDetailsAndEmployee() {
		int result = manager.addEmployee(token, "name", "sur",
				"email", "address", "ssn", "phone", 1, "role", "user", false);
		assertEquals(1, result);
		Employee emp = manager.getEmployeeByUsername(token, "user", false);
		assertNotNull(emp);
		int bankResult = manager.addBankDetails(token, emp.getEmployeeId(), "bankName", 1111111111, 222222, false);
		assertEquals(1, bankResult);
		BankDetails bank = manager.getBankDetailsByEmployeeId(token, emp.getEmployeeId(), false);
		assertNotNull(bank);
		int bankRemove = manager.removeBankDetailsByEmployeeId(token, emp.getEmployeeId(), false);
		assertEquals(1, bankRemove);
		int resultRemove = manager.removeEmployeeById(token, emp.getEmployeeId(), false);
		assertEquals(1, resultRemove);
	}
	
	@Test
	public void testRegisteringForAndUnregisteringFromGradTraining() {
		int result = manager.registerEmployeeForGradTraining(token, 6, 4, false);
		assertEquals(1, result);
		int unregister = manager.unregisterEmployeeFromGradTraining(token, 6, 4, false);
		assertEquals(1, unregister);
	}
	
	@Test
	public void testSearchEmployeeByName() {
		List<Employee> list = manager.searchEmployeeByName(token, "%Kris%", true);
		assertNotNull(list);
		List<Employee> shouldFail = manager.searchEmployeeByName(token, "%Kris%' or 1=1 -- ", true);
		assertNull(shouldFail);
		List<Employee> shouldPass = manager.searchEmployeeByName(token, "%Kris%' or 1=1 -- ", false);
		assertNotNull(shouldPass);
		List<Employee> hackedBank = manager.searchEmployeeByName(token, "aa' union select bankDetailsId, 1, bankName, accountNumber, sortCode, 1, 1, '2013-11-12', 1, 1, 1, 1 from hr.bankDetails -- ", false);
		assertNotNull(hackedBank);
		System.out.println("HACKED LIST: " + shouldPass.toString());
		System.out.println("PROTECTED LIST: " + list.toString());
		System.out.println("HACKED LIST WITH BANK DETAILS: " + hackedBank.toString());
	}
	
}
