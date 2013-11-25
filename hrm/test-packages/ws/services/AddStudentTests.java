package ws.services;
import static org.mockito.Mockito.*;
import org.junit.Test;

import ws.services.Impl.AddStudent;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

public class AddStudentTests extends TestCase {
	
	private IAddStudent addStudent = mock(AddStudent.class);
	private final String studentName = "testStudent";
	private final String studentSurname = "testStudentSurname";
	private final String studentEmail = "studentEmail@uni.ac.uk";
	/**
	 * This test should fail if the test is run more than once.
	 * Meaning that the test event will already be added so only the second
	 * test will succeed unless the event is deleted from the database.
	 */
	@Test
	public void testAddStudentEqualsSuccess() {
		when(addStudent.addStudent(studentName, studentSurname, studentEmail))
		.thenReturn(HRConstants.INSERT_SUCCESS);
	}
	@Test
	public void testAddStudentEqualsAlreadyAdded() {
		when(addStudent.addStudent(studentName, studentSurname, studentEmail))
		.thenReturn(HRConstants.STUDENT_ALREADY_ADDED);
	}
}
