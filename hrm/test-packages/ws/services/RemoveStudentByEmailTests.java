/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.RemoveStudentByEmail;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
/**
 * @author Kristiyan
 *
 */
public class RemoveStudentByEmailTests extends TestCase {
	private IRemoveStudentByEmail removeStudent = mock(RemoveStudentByEmail.class);
	private final String studentEmail = "studentEmail@uni.ac.uk";
	private final String nonExistentEmail = "blabla@asd.com";
	
	@Test
	public void testRemoveStudentByEmailSuccess() {
		when(removeStudent.removeStudentByEmail(studentEmail))
		.thenReturn(HRConstants.DELETE_SUCCESS);
	}
	@Test
	public void testRemoveStudentByEmailFailed() {
		when(removeStudent.removeStudentByEmail(nonExistentEmail))
		.thenReturn(HRConstants.STUDENT_DOES_NOT_EXIST);
	}
}
