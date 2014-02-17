/**
 * 
 */
package ws.services;

import java.util.Date;
import java.util.List;

import ws.dao.Student;

/**
 * @author Kristiyan
 *
 */
public interface IManageStudent {
	public int addStudent(final String token, final String studentName,
			final String studentSurname, final String studentEmail,
			final Date studentRegistered, boolean secure);
	
	public int removeStudentByEmail(final String token, final String studentEmail, boolean secure);
	
	public List<Student> searchStudentByName(final String token, final String searchPhrase, boolean secure);
	
	public List<Student> getAllStudents(final String token, boolean secure);
	
	public Student getStudentByEmail(final String token, final String studentEmail, boolean secure);
	
	public int registerStudentToEvent(final String token, final int studentId, final int eventId, boolean secure);
	public int unregisterStudentFromEvent(final String token, final int studentId, final int eventId, boolean secure);
	
}
