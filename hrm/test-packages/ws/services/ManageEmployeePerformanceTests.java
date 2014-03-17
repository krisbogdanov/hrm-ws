package ws.services;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import ws.dao.EmployeePerformance;
import ws.services.Impl.ManageEmployeePerformance;
import junit.framework.TestCase;

public class ManageEmployeePerformanceTests extends TestCase {
	private IManageEmployeePerformance manager = new ManageEmployeePerformance();
	private String writeToken = "writeToken";
	private String readToken = "readToken";
	
	@Test
	public void testGetEmpPerfById() {
		List<EmployeePerformance> listWithWritePerm = manager.getEmployeePerformanceByEmployeeId(writeToken, 1, false);
		assertNotNull(listWithWritePerm);
		System.out.println("WRITE PERM:  " + listWithWritePerm.toString());
		List<EmployeePerformance> listWithReadPerm = manager.getEmployeePerformanceByEmployeeId(readToken, 2, false);
		assertNull(listWithReadPerm);
		List<EmployeePerformance> listWithOwnerPerm = manager.getEmployeePerformanceByEmployeeId(readToken, 1, false);
		assertNotNull(listWithWritePerm);
		System.out.println("OWNER PERM:  " + listWithOwnerPerm.toString());
	}
	@Test
	public void testEditPerformance() {
		int edit = manager.editEmployeePerformance(writeToken, 3, "Edited", false);
		assertEquals(1, edit);
		int failEdit = manager.editEmployeePerformance(readToken, 3, "Fail", false);
		assertEquals(0, failEdit);
	}
	@Test
	public void testAddPerfThenRemoveIt() {
		int add = manager.addEmployeePerformance(writeToken, 3, "JUnit test", 2015, false);
		assertEquals(1, add);
		int failAdd = manager.addEmployeePerformance(readToken, 3, "fail test", 2015, false);
		assertEquals(0, failAdd);
		List<EmployeePerformance> listWithWritePerm = manager.getEmployeePerformanceByEmployeeId(writeToken, 3, false);
		assertNotNull(listWithWritePerm);
		Iterator<EmployeePerformance> it = listWithWritePerm.iterator();
		while(it.hasNext()) {
			EmployeePerformance perf = it.next();
			if(perf.getPerfDescription().equals("JUnit test")) {
				int failToRemove = manager.removeEmployeePerformanceByPerfId(readToken, perf.getPerfId(), false);
				assertEquals(0, failToRemove);
				int remove = manager.removeEmployeePerformanceByPerfId(writeToken, perf.getPerfId(), false);
				assertEquals(1, remove);
			}
		}
	}
}
