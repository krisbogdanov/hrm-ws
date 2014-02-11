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
	
	@Test
	public void testAddEmployeeThenGetItByUsernameAndThenRemove() {
		int result = manager.addEmployee("token", "name", "sur",
				"email", "address", "ssn", "phone", 1, "role", "user");
		assertEquals(1, result);
		Employee emp = manager.getEmployeeByUsername("token", "user");
		assertNotNull(emp);
		int resultRemove = manager.removeEmployeeById("token", emp.getEmployeeId());
		assertEquals(1, resultRemove);
	}
	@Test
	public void testEditEmployee() {
		int result = manager.editEmployeeById("token", 4, "Kris" , 
				"Bogdanov", "kris.b@hr.com", "320 plymouth grove", "SN-12-34-56-B", "0756145856182", 1, "Manager", "krisb");
		assertEquals(1, result);
	}
	
	@Test
	public void testGetEmployeeByIdAndThenChangePassword() {
		Employee emp = manager.getEmployeeById("token", 4);
		assertNotNull(emp);
		String randomPass = RandomStringUtils.randomAlphabetic(10);
		int result = manager.changeEmployeePassword("token", 4, emp.getEmployeePassword(), randomPass);
		assertEquals(1, result);
	}
	
	@Test
	public void testGetAllEmployees() {
		List<Employee> list = manager.getAllEmployees("token");
		assertNotNull(list);
	}
	
	@Test
	public void testGetBankDetailsByEmpId() {
		BankDetails details = manager.getBankDetailsByEmployeeId("token", 4);
		assertNotNull(details);
	}
	
	@Test
	public void testEditBankDetailsByEmpId() {
		int result = manager.editBankDetailsByEmployeeId("token", 4, "RBS", 124512541, 413512);
		assertEquals(1, result);
	}
	
	@Test
	public void testAddEmployeeThenBankDetailsThenRemoveBankDetailsAndEmployee() {
		int result = manager.addEmployee("token", "name", "sur",
				"email", "address", "ssn", "phone", 1, "role", "user");
		assertEquals(1, result);
		Employee emp = manager.getEmployeeByUsername("token", "user");
		assertNotNull(emp);
		int bankResult = manager.addBankDetails("token", emp.getEmployeeId(), "bankName", 1111111111, 222222);
		assertEquals(1, bankResult);
		BankDetails bank = manager.getBankDetailsByEmployeeId("token", emp.getEmployeeId());
		assertNotNull(bank);
		int bankRemove = manager.removeBankDetailsByEmployeeId("token", emp.getEmployeeId());
		assertEquals(1, bankRemove);
		int resultRemove = manager.removeEmployeeById("token", emp.getEmployeeId());
		assertEquals(1, resultRemove);
	}
	
	@Test
	public void testRegisteringForAndUnregisteringFromGradTraining() {
		int result = manager.registerEmployeeForGradTraining("token", 6, 4);
		assertEquals(1, result);
		int unregister = manager.unregisterEmployeeFromGradTraining("token", 6, 4);
		assertEquals(1, unregister);
	}
	
	@Test
	public void testSearchEmployeeByName() {
		List<Employee> list = manager.searchEmployeeByName("token", "%Kris%", false);
		assertNotNull(list);
		List<Employee> shouldFail = manager.searchEmployeeByName("token", "%Kris%' or 1=1 -- ", false);
		assertNull(shouldFail);
		List<Employee> shouldPass = manager.searchEmployeeByName("token", "%Kris%' or 1=1 -- ", true);
		assertNotNull(shouldPass);
		List<Employee> hackedBank = manager.searchEmployeeByName("token", "aa' union select bankDetailsId, 1, bankName, accountNumber, sortCode, 1, 1, '2013-11-12', 1, 1, 1, 1 from hr.bankDetails -- ", true);
		assertNotNull(hackedBank);
		System.out.println("HACKED LIST: " + shouldPass.toString());
		System.out.println("PROTECTED LIST: " + list.toString());
		System.out.println("HACKED LIST WITH BANK DETAILS: " + hackedBank.toString());
	}
	
}