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
	public static final String EMPLOYEE_ALREADY_ADDED = "Employee already added";
	public static final String EVENT_ALREADY_ADDED = "Event already added";
	public static final String STUDENT_ALREADY_ADDED = "Student already added";
	public static final String SELECT_EMPLOYEE_BY_EMAIL_STATEMENT = 
			"SELECT employeeEmail FROM hr.employee WHERE employeeEmail = ?;";
	public static final String INSERT_EMPLOYEE_STATEMENT = 
    		"INSERT INTO hr.employee VALUES (default, ?, ?, ?, ?, ?, ?, ?);";
	public static final String INSERT_EVENT_STATEMENT = 
			"INSERT INTO hr.event VALUES(default, ?, ?, ?, ?, ?);";
	public static final String SELECT_EVENT_BY_NAME_STATEMENT =
			"SELECT eventName FROM hr.event WHERE eventName = ?;";
	public static final String INSERT_STUDENT_STATEMENT = 
			"INSERT INTO hr.student VALUES (default, ?, ?, ?, ?);";
	public static final String SELECT_STUDENT_BY_EMAIL_STATEMENT =
			"SELECT studentEmail FROM hr.student WHERE studentEmail = ?;";
}
