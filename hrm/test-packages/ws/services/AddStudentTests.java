package ws.services;

import ws.services.Impl.AddStudent;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

public class AddStudentTests extends TestCase {
	
	private IAddStudent addStudent = new AddStudent();
	private final String studentName = "testStudent";
	private final String studentSurname = "testStudentSurname";
	private final String studentEmail = "studentEmail@uni.ac.uk";
	/**
	 * This test should fail if the test is run more than once.
	 * Meaning that the test event will already be added so only the second
	 * test will succeed unless the event is deleted from the database.
	 */
	public void testAddStudentEqualsSuccess() {
		String result = addStudent.addStudent(studentName, studentSurname, studentEmail);
		assertEquals(HRConstants.INSERT_SUCCESS, result);
	}
	public void testAddStudentEqualsAlreadyAdded() {
		String result = addStudent.addStudent(studentName, studentSurname, studentEmail);
		assertEquals(HRConstants.STUDENT_ALREADY_ADDED, result);
	}
}
