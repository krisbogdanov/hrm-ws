/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import ws.dao.Student;
import ws.services.IManageStudent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageStudent implements IManageStudent {
	
	
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#addStudent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public int addStudent(String token, String studentName,
			String studentSurname, String studentEmail, Date studentRegistered) {
		try {
			Student student = getStudentByEmail(token, studentEmail);
			if(student == null) {
				PreparedStatement insert = connection.
						prepareStatement(HRConstants.INSERT_STUDENT);
				insert.setString(0, studentName);
				insert.setString(1, studentSurname);
				insert.setString(2, studentEmail);
				insert.setObject(3, studentRegistered);
				int result = insert.executeUpdate();
				insert.close();
				if(result == 0) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#removeStudentByEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeStudentByEmail(String token, String studentEmail) {
		try {
			PreparedStatement delete = connection.
					prepareStatement(HRConstants.DELETE_STUDENT_BY_EMAIL);
			delete.setString(0, studentEmail);
			int result = delete.executeUpdate();
			delete.close();
			if(result == 0) {
				return 0;
			} else {
				return 1;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#searchStudentByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Student> searchStudentByName(String token, String searchPhrase) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SEARCH_STUDENT_BY_NAME);
			select.setString(0, searchPhrase);
			select.setString(1, searchPhrase);
			ResultSet result = select.executeQuery();
		
			List<Student> list = generateStudentList(result);
			select.close();
			if(list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#getAllStudents(java.lang.String)
	 */
	@Override
	public List<Student> getAllStudents(String token) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_ALL_STUDENTS);
			ResultSet result = select.executeQuery();
			
			List<Student> list = generateStudentList(result); 
			select.close();
			if(list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<Student> generateStudentList(ResultSet result)
			throws SQLException {
		List<Student> list = new ArrayList<Student>();
		while(result.next()) {
			Student student = new Student(
					result.getInt(HRConstants.STUDENT_ID), 
					result.getString(HRConstants.STUDENT_NAME), 
					result.getString(HRConstants.STUDENT_SURNAME), 
					result.getString(HRConstants.STUDENT_EMAIL), 
					result.getDate(HRConstants.STUDENT_REGISTERED));
			list.add(student);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#getStudentByEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public Student getStudentByEmail(String token, String studentEmail) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_STUDENT_BY_EMAIL);
			select.setString(0, studentEmail);
			ResultSet result = select.executeQuery();
			
			if(result.next()) {
				Student student = new Student(
						result.getInt(HRConstants.STUDENT_ID), 
						result.getString(HRConstants.STUDENT_NAME), 
						result.getString(HRConstants.STUDENT_SURNAME), 
						result.getString(HRConstants.STUDENT_EMAIL), 
						result.getDate(HRConstants.STUDENT_REGISTERED));
				select.close();
				return student;
			} else {
				select.close();
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#registerStudentToEvent(java.lang.String, int, int)
	 */
	@Override
	public int registerStudentToEvent(String token, int studentId, int eventId) {
		try {
			PreparedStatement insert = connection.
					prepareStatement(HRConstants.REGISTER_STUDENT_FOR_EVENT);
			insert.setInt(0, studentId);
			insert.setInt(1, eventId);
			int result = insert.executeUpdate();
			insert.close();
			if(result == 0) {
				return 0;
			} else {
				return 1;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageStudent#unregisterStudetnFromEvent(java.lang.String, int)
	 */
	@Override
	public int unregisterStudetnFromEvent(String token, int studentId, int eventId) {
		try {
			PreparedStatement delete = connection.
					prepareStatement(HRConstants.UNREGISTER_STUDENT_FROM_EVENT);
			delete.setInt(0, studentId);
			delete.setInt(1, eventId);
			int result = delete.executeUpdate();
			delete.close();
			if(result == 0) {
				return 0;
			} else {
				return 1;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
