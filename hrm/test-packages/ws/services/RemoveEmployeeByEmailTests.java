/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.RemoveEmployeeByEmail;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
/**
 * @author Kristiyan
 *
 */
public class RemoveEmployeeByEmailTests extends TestCase {
	
	private IRemoveEmployeeByEmail removeEmployee = mock(RemoveEmployeeByEmail.class);
	private final String empEmail = "test.test@hrm.com";
	private final String nonExistentEmail = "test@hrm.com";
	
	@Test
	public void testRemoveEmployeeByEmailSuccess() {
		when(removeEmployee.removeEmployeeByEmail(empEmail))
		.thenReturn(HRConstants.DELETE_SUCCESS);
	}
	@Test
	public void testRemoveEmployeeByEmailFailed() {
		when(removeEmployee.removeEmployeeByEmail(nonExistentEmail))
		.thenReturn(HRConstants.EMPLOYEE_DOES_NOT_EXIST);
	}
}
