/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import ws.dao.Employee;
import ws.security.AuthenticationManager;
import ws.security.InputValidationManager;
import ws.security.Impl.AuthenticationManagerImpl;
import ws.security.Impl.InputValidationManagerImpl;
import ws.services.IManageToken;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageToken implements IManageToken {
	
	private static final String SELECT_TOKEN_BY_EMPLOYEE_ID = "SELECT * FROM hr.Token WHERE employeeId = ?;";
	private static final String DELETE_TOKEN = "DELETE FROM hr.Token WHERE token = ?;";
	private static final String INSERT_TOKEN = "INSERT INTO hr.Token VALUES(?, ?, ?, ?);";
	private final AuthenticationManager authenticator = new AuthenticationManagerImpl();
	private final Connection connection = DatabaseConnection.getConnection();
	private final InputValidationManager validationManager = new InputValidationManagerImpl();
	@Override
	public String getToken(final String username, final String password, boolean secure) {
		if(secure) {
			if(!validationManager.usernameValidation(username) ||
					!validationManager.passwordValidation(password)) {
				return null;
			}
		}
		return generateToken(username, password);
	}
	private String generateToken(final String username, final String password) {
		Employee employee = authenticator.authenticate(username, password);
		//get the whole Employee object
		if(employee != null) {
			String token = UUID.randomUUID().toString().toUpperCase();
			Date expires = new Date(System.currentTimeMillis() + 86400 * 1000 * 2); //48 hours from now
			try {
				//does the employee have a token already?
				PreparedStatement oldToken = connection.prepareStatement(SELECT_TOKEN_BY_EMPLOYEE_ID);
				oldToken.setInt(1, employee.getEmployeeId());
				ResultSet oldTokenResult = oldToken.executeQuery();
				if(oldTokenResult.next()) {
					deleteToken(oldTokenResult.getString(HRConstants.TOKEN));
				}
				int permissions = 0; //read only
				PreparedStatement department = connection.
						prepareStatement(HRConstants.SELECT_DEPARTMENT_BY_ID);
				department.setInt(1, employee.getEmployeeDepartment());
				ResultSet departmentResult = department.executeQuery();
				if(departmentResult.next()) {
					String dep = departmentResult.getString(HRConstants.DEPARTMENT);
					if(dep.equals("Human Resources")) {
						permissions = 1; //read/write
					}
				} else {
					return null;
				}
				PreparedStatement insertToken = connection.
					prepareStatement(INSERT_TOKEN);
				insertToken.setString(1, token);
				insertToken.setInt(2, employee.getEmployeeId());
				insertToken.setObject(3, expires);
				insertToken.setInt(4, permissions);
				int result = insertToken.executeUpdate();
				insertToken.close();
				if(result != 0) {
					return token;
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return HRConstants.ACCESS_DENIED;
			}
		} else {
			return HRConstants.ACCESS_DENIED;
		}
	}
	@Override
	public void deleteToken(String token) {
		try {
			if(token != null) {
				PreparedStatement deleteToken = connection.
						prepareStatement(DELETE_TOKEN);
				deleteToken.setString(1, token);
				deleteToken.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
