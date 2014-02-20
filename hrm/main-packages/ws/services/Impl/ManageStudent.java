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

import javax.jws.WebParam;
import javax.jws.WebService;


import ws.dao.Student;
import ws.security.AuthorizationManager;
import ws.security.InputValidationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.security.Impl.InputValidationManagerImpl;
import ws.services.IManageStudent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
@WebService(targetNamespace = "http://Impl.services.ws/", portName = "ManageStudentPort", serviceName = "ManageStudentService")
public class ManageStudent implements IManageStudent {
	
	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();
	private final InputValidationManager validationManager = new InputValidationManagerImpl();
	@Override
	public int addStudent(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String studentName,
			@WebParam(name = "arg2") String studentSurname, @WebParam(name = "arg3") String studentEmail, @WebParam(name = "arg4") Date studentRegistered, @WebParam(name = "arg5") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(studentName) ||
						!validationManager.textValidation(studentSurname) ||
						!validationManager.emailValidation(studentEmail) ||
						!validationManager.dateValidation(studentRegistered)) {
					return 0;
				}
			}
			Student student = getStudentByEmail(token, studentEmail, secure);
			if(student == null) {
				PreparedStatement insert = connection.
						prepareStatement(HRConstants.INSERT_STUDENT);
				insert.setString(1, studentName);
				insert.setString(2, studentSurname);
				insert.setString(3, studentEmail);
				insert.setObject(4, studentRegistered);
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

	@Override
	public int removeStudentByEmail(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String studentEmail, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.emailValidation(studentEmail)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				Student student = getStudentByEmail(token, studentEmail, secure);
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_STUDENT_BY_EMAIL);
				delete.setString(1, studentEmail);
				int result = delete.executeUpdate();
				PreparedStatement deleteMappings = connection.
						prepareStatement(HRConstants.DELETE_STUDENT_MAPPINGS);
				deleteMappings.setInt(1, student.getStudentId());
				deleteMappings.executeUpdate();
				delete.close();
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

	@Override
	public List<Student> searchStudentByName(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String searchPhrase, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(searchPhrase)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SEARCH_STUDENT_BY_NAME);
				select.setString(1, searchPhrase);
				select.setString(2, searchPhrase);
				ResultSet result = select.executeQuery();
				List<Student> list = generateStudentList(result);
				select.close();
				if(list.isEmpty()) {
					return null;
				} else {
					return list;
				}
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> getAllStudents(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") boolean secure) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
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
			} else {
				return null;
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

	@Override
	public Student getStudentByEmail(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String studentEmail, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.emailValidation(studentEmail)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_STUDENT_BY_EMAIL);
				select.setString(1, studentEmail);
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
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int registerStudentToEvent(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int studentId, @WebParam(name = "arg2") int eventId, @WebParam(name = "arg3") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(studentId) ||
						!validationManager.integerValidation(eventId)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement check = connection.
						prepareStatement(HRConstants.SELECT_EXACT_STUDENT_TO_EVENT_MAPPING);
				check.setInt(1, studentId);
				check.setInt(2, eventId);
				ResultSet checkResult = check.executeQuery();
				if(!checkResult.next()) { //if student not already registered to exactly that event do it.
					PreparedStatement insert = connection.
							prepareStatement(HRConstants.REGISTER_STUDENT_FOR_EVENT);
					insert.setInt(1, studentId);
					insert.setInt(2, eventId);
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
			} else {
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int unregisterStudentFromEvent(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int studentId, @WebParam(name = "arg2") int eventId, @WebParam(name = "arg3") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(studentId) ||
						!validationManager.integerValidation(eventId)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.UNREGISTER_STUDENT_FROM_EVENT);
				delete.setInt(1, studentId);
				delete.setInt(2, eventId);
				int result = delete.executeUpdate();
				delete.close();
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

}
