/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import ws.dao.EmployeePerformance;
import ws.security.AuthorizationManager;
import ws.security.InputValidationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.security.Impl.InputValidationManagerImpl;
import ws.services.IManageEmployeePerformance;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
@WebService(targetNamespace = "http://Impl.services.ws/", portName = "ManageEmployeePerformancePort", serviceName = "ManageEmployeePerformanceService")
public class ManageEmployeePerformance implements IManageEmployeePerformance {
	
	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();
	private final InputValidationManager validationManager = new InputValidationManagerImpl();
	@Override
	public int addEmployeePerformance(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId,
			@WebParam(name = "arg2") String perfDescription, @WebParam(name = "arg3") int year, @WebParam(name = "arg4") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId) ||
						!validationManager.textValidation(perfDescription) ||
						!validationManager.integerValidation(year)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement insertStatement = connection.
						prepareStatement(HRConstants.INSERT_EMP_PERFORMANCE);
				insertStatement.setInt(1, employeeId);
				insertStatement.setString(2, perfDescription);
				insertStatement.setInt(3, year);
				int result = insertStatement.executeUpdate();
				insertStatement.close();
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
	public int editEmployeePerformance(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int perfId,
			@WebParam(name = "arg2") String perfDescription, @WebParam(name = "arg3") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(perfId) ||
						!validationManager.textValidation(perfDescription)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement update = connection.
						prepareStatement(HRConstants.UPDATE_EMP_PERFORMANCE);
				update.setString(1, perfDescription);
				update.setInt(2, perfId);
				int result = update.executeUpdate();
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
	public int removeEmployeePerformanceByPerfId(@WebParam(name = "arg0") String token,
			@WebParam(name = "arg1") int perfId, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(perfId)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_EMP_PERFORMANCE);
				delete.setInt(1, perfId);
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

	@Override
	public List<EmployeePerformance> getEmployeePerformanceByEmployeeId(@WebParam(name = "arg0") String token,
			@WebParam(name = "arg1") int employeeId, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) ||
					authManager.isTheOwnerOf(token, employeeId)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_EMP_PERFORMANCE_BY_EMP_ID);
				select.setInt(1, employeeId);
				ResultSet result = select.executeQuery();
				List<EmployeePerformance> list = generateEmployeePerformanceList(result);
				if(list.isEmpty()) {
					return null;
				} else {
					return list;
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<EmployeePerformance> generateEmployeePerformanceList(
			ResultSet result) throws SQLException {
		List<EmployeePerformance> list = new ArrayList<EmployeePerformance>();
		while(result.next()) {
			EmployeePerformance performance = new EmployeePerformance(
					result.getInt(HRConstants.PERF_ID),
					result.getInt(HRConstants.EMPLOYEE_ID),
					result.getString(HRConstants.PERF_DESCRIPTION),
					result.getInt(HRConstants.PERF_YEAR));
			list.add(performance);
		}
		return list;
	}

}
