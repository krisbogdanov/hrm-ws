/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.RemoveEmployeeByEmail;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class RemoveEmployeeByEmailTests extends TestCase {
	
	private IRemoveEmployeeByEmail removeEmployee = new RemoveEmployeeByEmail();
	private final String empEmail = "test.test@hrm.com";
	private final String nonExistentEmail = "test@hrm.com";
	
	@Test
	public void testRemoveEmployeeByEmailSuccess() {
		String result = removeEmployee.removeEmployeeByEmail(empEmail);
		assertEquals(HRConstants.DELETE_SUCCESS, result);
	}
	@Test
	public void testRemoveEmployeeByEmailFailed() {
		String result = removeEmployee.removeEmployeeByEmail(nonExistentEmail);
		assertEquals(HRConstants.EMPLOYEE_DOES_NOT_EXIST, result);
	}
}
