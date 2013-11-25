/**
 * 
 */
package ws.services;

import org.junit.Test;
import static org.mockito.Mockito.*;
import ws.services.Impl.RegisterStudentForEvent;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class RegisterStudentForEventTests extends TestCase {
	private IRegisterStudentForEvent register = mock(RegisterStudentForEvent.class);
	private int studentId = 1;
	private int eventId = 1;
	
	@Test
	public void testRegisterStudentForEventSuccess() {
		when(register.registerStudentForEvent(studentId, eventId))
		.thenReturn(HRConstants.INSERT_SUCCESS);
	}
	@Test
	public void testRegisterStudentForEventAlreadyRegistered() {
		when(register.registerStudentForEvent(studentId, eventId))
		.thenReturn(HRConstants.STUDENT_ALREADY_REGISTERED);
	}
}
