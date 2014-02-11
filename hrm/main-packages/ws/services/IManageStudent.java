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
			final Date studentRegistered);
	
	public int removeStudentByEmail(final String token, final String studentEmail);
	
	public List<Student> searchStudentByName(final String token, final String searchPhrase);
	
	public List<Student> getAllStudents(final String token);
	
	public Student getStudentByEmail(final String token, final String studentEmail);
	
	public int registerStudentToEvent(final String token, final int studentId, final int eventId);
	public int unregisterStudetnFromEvent(final String token, final int studentId, final int eventId);
	
}
