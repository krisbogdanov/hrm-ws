/**
 * 
 */
package ws.utils.Impl;

/**
 * @author Kristiyan
 *
 */
public class HRConstants {
	public static final String ACCESS_DENIED = "Access Denied!";
	public static final String INSERT_SUCCESS = "Success";
	public static final String DELETE_SUCCESS = "Record successfully deleted";
	public static final String EMPLOYEE_DOES_NOT_EXIST = "Employee does not exist";
	public static final String EVENT_DOES_NOT_EXIST = "Event does not exist";
	public static final String STUDENT_DOES_NOT_EXIST = "Student does not exist";
	public static final String EMPLOYEE_ALREADY_ADDED = "Employee already added";
	public static final String EVENT_ALREADY_ADDED = "Event already added";
	public static final String STUDENT_ALREADY_ADDED = "Student already added";
	public static final String STUDENT_ALREADY_REGISTERED = "Student already registered";
	public static final String SELECT_REGISTRATION_BY_IDS = 
			"SELECT * FROM hr.StudentToEvent WHERE studentId = ? AND eventId = ?;";
	public static final String SELECT_EMPLOYEE_BY_EMAIL_STATEMENT = 
			"SELECT employeeEmail FROM hr.Employee WHERE employeeEmail = ?;";
	public static final String INSERT_STUDENT_TO_EVENT = 
			"INSERT INTO hr.StudentToEvent VALUES(default, ?, ?);";
	public static final String INSERT_EMPLOYEE_STATEMENT = 
    		"INSERT INTO hr.Employee VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String INSERT_EVENT_STATEMENT = 
			"INSERT INTO hr.Event VALUES(default, ?, ?, ?, ?, ?);";
	public static final String SELECT_EVENT_BY_NAME_STATEMENT =
			"SELECT eventName FROM hr.Event WHERE eventName = ?;";
	public static final String INSERT_STUDENT_STATEMENT = 
			"INSERT INTO hr.Student VALUES (default, ?, ?, ?, ?);";
	public static final String SELECT_STUDENT_BY_EMAIL_STATEMENT =
			"SELECT studentEmail FROM hr.Student WHERE studentEmail = ?;";
	public static final String DELETE_EMPLOYEE_BY_EMAIL = 
			"DELETE FROM hr.Employee WHERE employeeEmail = ?;";
	public static final String DELETE_STUDENT_BY_EMAIL = 
			"DELETE FROM hr.Student WHERE studentEmail = ?;";
	public static final String DELETE_EVENT_BY_NAME = 
			"DELETE FROM hr.Event WHERE eventName = ?;";
}
