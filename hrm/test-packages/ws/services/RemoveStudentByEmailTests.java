/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.RemoveStudentByEmail;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class RemoveStudentByEmailTests extends TestCase {
	private IRemoveStudentByEmail removeStudent = new RemoveStudentByEmail();
	private final String studentEmail = "studentEmail@uni.ac.uk";
	private final String nonExistentEmail = "blabla@asd.com";
	
	@Test
	public void testRemoveStudentByEmailSuccess() {
		String result = removeStudent.removeStudentByEmail(studentEmail);
		assertEquals(HRConstants.DELETE_SUCCESS, result);
	}
	@Test
	public void testRemoveStudentByEmailFailed() {
		String result = removeStudent.removeStudentByEmail(nonExistentEmail);
		assertEquals(HRConstants.STUDENT_DOES_NOT_EXIST, result);
	}
}
