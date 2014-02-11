/**
 * 
 */
package ws.utils.Impl;

/**
 * @author Kristiyan
 *
 */
public class HRConstants {
	public static final String BANK_DETAILS_ID = "bankDetailsId";
	public static final String BANK_NAME = "bankName";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String SORT_CODE = "sortCode";
	public static final int INSERT_SUCCESS = 1;
	public static final int EMPLOYEE_ALREADY_ADDED = 0;
	public static final String TEMP_PASSWORD = "HR123456";
	public static final String EMPLOYEE_PHONE = "employeePhone";
	public static final String EMPLOYEE_DEPARTMENT = "employeeDepartment";
	public static final String EMPLOYEE_JOINED = "employeeJoined";
	public static final String EMPLOYEE_PASSWORD = "employeePassword";
	public static final String EMPLOYEE_USERNAME = "employeeUsername";
	public static final String EMPLOYEE_SSN = "employeeSSN";
	public static final String EMPLOYEE_ROLE = "employeeRole";
	public static final String EMPLOYEE_ADDRESS = "employeeAddress";
	public static final String EMPLOYEE_EMAIL = "employeeEmail";
	public static final String EMPLOYEE_SURNAME = "employeeSurname";
	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String STUDENT_REGISTERED = "studentRegistered";
	public static final String STUDENT_EMAIL = "studentEmail";
	public static final String STUDENT_SURNAME = "studentSurname";
	public static final String STUDENT_NAME = "studentName";
	public static final String STUDENT_ID = "studentId";
	public static final String GRAD_TRAINING_ENDS = "gradTrainingEnds";
	public static final String GRAD_TRAINING_STARTS = "gradTrainingStarts";
	public static final String GRAD_TRAINING_LOCATION = "gradTrainingLocation";
	public static final String GRAD_TRAINING_ID = "gradTrainingId";
	public static final String PERF_YEAR = "perfYear";
	public static final String PERF_DESCRIPTION = "perfDescription";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String PERF_ID = "perfId";
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String DEPARTMENT = "department";
	public static final String EVENT_CAPACITY = "eventCapacity";
	public static final String EVENT_DURATION_IN_MINUTES = "eventDurationInMinutes";
	public static final String EVENT_DATE = "eventDate";
	public static final String EVENT_LOCATION = "eventLocation";
	public static final String EVENT_NAME = "eventName";
	public static final String EVENT_ID = "eventId";
	public static final String ACCESS_DENIED = "Access Denied!";
	public static final String INSERT_EMPLOYEE_STATEMENT = 
    		"INSERT INTO hr.Employee VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String SELECT_GRAD_TRAINING_BY_LOCATION = 
			"SELECT * FROM hr.GraduateTraining WHERE gradTrainingLocation = ?;";
	public static final String INSERT_GRAD_TRAINING_STATEMENT =
			"INSERT INTO hr.GraduateTraining VALUES(default, ?, ?, ?);";
	public static final String REMOVE_GRAD_TRAINING_BY_LOCATION =
			"DELETE FROM hr.GraduateTraining WHERE gradTrainingLocation = ?;";
	public static final String REMOVE_GRAD_TRAINING_MAPPINGS =
			"DELETE FROM hr.EmployeeToGradTrainining WHERE gradTrainingId = ?;";
	public static final String SELECT_ALL_GRAD_TRAININGS = 
			"SELECT * FROM hr.GraduateTraining;";
	public static final String SELECT_EMP_PERFORMANCE_BY_EMP_ID = 
			"SELECT * FROM hr.EmployeePerformance WHERE employeeId = ?;";
	public static final String DELETE_EMP_PERFORMANCE = 
			"DELETE FROM hr.EmployeePerformance WHERE perfId = ?;";
	public static final String UPDATE_EMP_PERFORMANCE = 
			"UPDATE hr.EmployeePerformance SET perfDescription = ? WHERE perfId = ?;";
	public static final String INSERT_EMP_PERFORMANCE = 
			"INSERT INTO hr.EmployeePerformance VALUES(default, ?, ?, ?);";
	public static final String SELECT_EMP_DEPARTMENT_BY_NAME = 
			"SELECT * FROM hr.EmployeeDepartment WHERE department = ?;";
	public static final String SELECT_ALL_EMP_DEPARTMENT = 
			"SELECT * FROM hr.EmployeeDepartment;";
	public static final String DELETE_EMP_DEPARTMENT = 
			"DELETE FROM hr.EmployeeDepartment WHERE department = ?;";
	public static final String INSERT_EMP_DEPARTMENT = 
			"INSERT INTO hr.EmployeeDepartment VALUES(default, ?);";
	public static final String SELECT_ALL_EVENTS = 
			"SELECT * FROM hr.Event;";
	public static final String SELECT_EVENT_BY_NAME = 
			"SELECT * FROM hr.Event WHERE eventName = ?;";
	public static final String UPDATE_EVENT = 
			"UPDATE hr.Event SET eventName = ?, eventLocation = ?," +
			" eventDate = ?, eventDurationInMinutes = ?, eventCapacity = ?" +
			" WHERE eventId = ?;";
	public static final String DELETE_EVENT_BY_NAME = 
			"DELETE FROM hr.Event WHERE eventName = ?;";
	public static final String INSERT_EVENT = 
			"INSERT INTO hr.Event VALUES(default, ?, ?, ?, ?, ?);";
	public static final String SELECT_STUDENT_BY_EMAIL =
			"SELECT * FROM hr.Student WHERE studentEmail = ?;";
	public static final String SELECT_ALL_STUDENTS =
			"SELECT * FROM hr.Student;";
	public static final String DELETE_STUDENT_BY_EMAIL =
			"DELETE FROM hr.Student WHERE studentEmail = ?;";
	public static final String INSERT_STUDENT =
			"INSERT INTO hr.Student VALUES(default, ?, ?, ?, ?);";
	public static final String SEARCH_STUDENT_BY_NAME =
			"SELECT * FROM hr.Student WHERE studentName LIKE '%?%' " +
			"OR studentSurname LIKE '%?%';";
	public static final String REGISTER_STUDENT_FOR_EVENT = 
			"INSERT INTO hr.StudentToEvent VALUES(default, ?, ?);";
	public static final String UNREGISTER_STUDENT_FROM_EVENT = 
			"DELETE FROM hr.StudentToEvent WHERE studentId = ? AND eventId = ?;";
	public static final String SELECT_EMPLOYEE_BY_USERNAME = 
			"SELECT * FROM hr.Employee WHERE employeeUsername LIKE ?;";
	public static final String SELECT_EMPLOYEE_BY_ID = 
			"SELECT * FROM hr.Employee WHERE employeeId = ?;";
	public static final String INSERT_EMPLOYEE =
			"INSERT INTO hr.Employee VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String DELETE_EMPLOYEE_BY_ID =
			"DELETE FROM hr.Employee WHERE employeeId = ?;";
	public static final String SEARCH_EMPLOYEE_BY_NAME =
			"SELECT * FROM hr.Employee WHERE employeeName LIKE ? " +
			"OR employeeSurname LIKE ?;";
	public static final String SELECT_ALL_EMPLOYEES =
			"SELECT * FROM hr.Employee;";
	public static final String REGISTER_EMPLOYEE_TO_GRAD_TRAINING = 
			"INSERT INTO hr.EmployeeToGradTraining VALUES(default, ?, ?);";
	public static final String UNREGISTER_EMPLOYEE_FROM_GRAD_TRAINING =
			"DELETE FROM hr.EmployeeToGradTraining WHERE employeeId = ? AND gradTrainingId = ?;";
	public static final String SELECT_EMPLOYEE_TO_GRAD_TRAINING = 
			"SELECT * FROM hr.EmployeeToGradTraining WHERE employeeId = ? AND gradTrainingId = ?;";
	public static final String UPDATE_EMPLOYEE_BY_ID = 
			"UPDATE hr.Employee SET employeeName = ?, employeeSurname = ?, employeeEmail = ?, employeeAddress = ?," +
			" employeeSSN = ?, employeePhone = ?, employeeDepartment = ?, employeeRole = ?, employeeUsername = ?" +
			" WHERE employeeId = ?;";
	public static final String UPDATE_EMPLOYEE_PASSWORD = 
			"UPDATE hr.Employee SET employeePassword = ? WHERE employeeId = ?;";
	public static final String SELECT_BANK_DETAILS_BY_EMP_ID = 
			"SELECT * FROM hr.BankDetails WHERE employeeId = ?;";
	public static final String INSERT_BANK_DETAILS = 
			"INSERT INTO hr.BankDetails VALUES(default, ?, ?, ?, ?);";
	public static final String DELETE_BANK_DETAILS_BY_EMPLOYEE_ID =
			"DELETE FROM hr.BankDetails WHERE employeeId = ?;";
	public static final String UPDATE_BANK_DETAILS_BY_EMPLOYEE_ID =
			"UPDATE hr.BankDetails SET bankName = ?, accountNumber = ?, sortCode = ?" +
			" WHERE employeeId = ?;";
	public static final String SELECT_DEPARTMENT_BY_ID =
			"SELECT department FROM hr.EmployeeDepartment WHERE departmentId = ?;";
}
