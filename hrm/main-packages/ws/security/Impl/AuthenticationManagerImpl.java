/**
 * 
 */
package ws.security.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ws.dao.Employee;
import ws.security.AuthenticationManager;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class AuthenticationManagerImpl implements AuthenticationManager {
	
	private static final String AUTHENTICATE_QUERY = 
			"SELECT * FROM hr.Employee WHERE employeeUsername = ? AND employeePassword = ?;";
	private final Connection connection = DatabaseConnection.getConnection();
	
	/* (non-Javadoc)
	 * @see ws.security.Authenticator#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public Employee authenticate(final String username, final String password) {
		if(username != null || password != null) {
			try {
				PreparedStatement select = connection.prepareStatement(AUTHENTICATE_QUERY);
				select.setString(1, username);
				select.setString(2, password);
				ResultSet result = select.executeQuery();
				if(result.next()) {
					Employee employee = new Employee(
							result.getInt(HRConstants.EMPLOYEE_ID),
							result.getString(HRConstants.EMPLOYEE_NAME), 
							result.getString(HRConstants.EMPLOYEE_SURNAME), 
							result.getString(HRConstants.EMPLOYEE_EMAIL), 
							result.getString(HRConstants.EMPLOYEE_ADDRESS), 
							result.getString(HRConstants.EMPLOYEE_SSN), 
							result.getString(HRConstants.EMPLOYEE_USERNAME), 
							result.getString(HRConstants.EMPLOYEE_PASSWORD), 
							result.getDate(HRConstants.EMPLOYEE_JOINED), 
							result.getInt(HRConstants.EMPLOYEE_DEPARTMENT), 
							result.getString(HRConstants.EMPLOYEE_PHONE),
							result.getString(HRConstants.EMPLOYEE_ROLE));
					select.close();
					return employee;
				} else {
					select.close();
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

}
