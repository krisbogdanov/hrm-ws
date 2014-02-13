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

import ws.dao.EmployeePerformance;
import ws.security.AuthorizationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.services.IManageEmployeePerformance;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployeePerformance implements IManageEmployeePerformance {
	
	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();

	@Override
	public int addEmployeePerformance(String token, int employeeId,
			String perfDescription, int year) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement insertStatement = connection.
						prepareStatement(HRConstants.INSERT_EMP_PERFORMANCE);
				insertStatement.setInt(0, employeeId);
				insertStatement.setString(1, perfDescription);
				insertStatement.setInt(2, year);
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
	public int editEmployeePerformance(String token, int perfId,
			String perfDescription) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement update = connection.
						prepareStatement(HRConstants.UPDATE_EMP_PERFORMANCE);
				update.setString(0, perfDescription);
				update.setInt(1, perfId);
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
	public int removeEmployeePerformanceByPerfId(String token,
			int perfId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_EMP_PERFORMANCE);
				delete.setInt(0, perfId);
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
	public List<EmployeePerformance> getEmployeePerformanceByEmployeeId(String token,
			int employeeId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) ||
					authManager.isTheOwnerOf(token, employeeId)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_EMP_PERFORMANCE_BY_EMP_ID);
				select.setInt(0, employeeId);
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
