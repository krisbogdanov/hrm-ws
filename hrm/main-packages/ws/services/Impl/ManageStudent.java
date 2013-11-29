/**
 * 
 */
package ws.services.Impl;

import java.util.Date;
import java.util.List;

import ws.dao.Student;
import ws.services.IManageStudent;

/**
 * @author Kristiyan
 *
 */
public class ManageStudent implements IManageStudent {

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#addStudent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public int addStudent(String token, String studentName,
			String studentSurname, String studentEmail, Date studentRegistered) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#removeStudentByEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeStudentByEmail(String token, String studentEmail) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#searchStudentByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Student> searchStudentByName(String token, String searchPhrase) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#getAllStudents(java.lang.String)
	 */
	@Override
	public List<Student> getAllStudents(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#getStudentByEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public Student getStudentByEmail(String token, String studentEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#registerStudentToEvent(java.lang.String, int, int)
	 */
	@Override
	public int registerStudentToEvent(String token, int studentId, int eventId) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#unregisterStudetnFromEvent(java.lang.String, int)
	 */
	@Override
	public int unregisterStudetnFromEvent(String token, int studentId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
