/**
 * 
 */
package ws.services;

import java.util.List;

import org.junit.Test;

import ws.dao.EmployeeDepartment;
import ws.services.Impl.ManageEmployeeDepartment;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployeeDepartmentTests extends TestCase {
	private IManageEmployeeDepartment manager = new ManageEmployeeDepartment();
	private String writeToken = "writeToken";
	private String readToken = "readToken";
	
	@Test
	public void testAddDepartmentThenGetItThenDeleteIt() {
		int add = manager.addEmployeeDepartment(writeToken, "Test");
		assertEquals(1, add);
		int failToAdd = manager.addEmployeeDepartment(readToken, "Fail");
		assertEquals(0, failToAdd);
		EmployeeDepartment dep = manager.getEmployeeDepartmentByName(writeToken, "Test");
		assertNotNull(dep);
		int failToDelete = manager.removeEmployeeDepartment(readToken, dep.getDepartment());
		assertEquals(0, failToDelete);
		int delete = manager.removeEmployeeDepartment(writeToken, dep.getDepartment());
		assertEquals(1, delete);
	}
	
	@Test
	public void testGetAllDepartmentsWithDiffPermissions() {
		List<EmployeeDepartment> listForWritePerm = manager.getAllEmployeeDepartments(writeToken);
		assertNotNull(listForWritePerm);
		System.out.println("WRITE PERM: " + listForWritePerm.toString());
		List<EmployeeDepartment> listForReadPerm = manager.getAllEmployeeDepartments(readToken);
		assertNotNull(listForReadPerm);
		System.out.println("READ PERM: " + listForReadPerm.toString());
	}
	@Test
	public void testGetDepartmentByNameWithDiffPermissions() {
		EmployeeDepartment writePermDep = manager.getEmployeeDepartmentByName(writeToken, "Human Resources");
		assertNotNull(writePermDep);
		System.out.println("Write PERM DEP NAME: " + writePermDep.getDepartment());
		EmployeeDepartment readPermDep = manager.getEmployeeDepartmentByName(readToken, "Technology");
		assertNotNull(readPermDep);
		System.out.println("Write PERM DEP NAME: " + readPermDep.getDepartment());
	}
}
