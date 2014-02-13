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

import ws.dao.EmployeeDepartment;
import ws.security.AuthorizationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.services.IManageEmployeeDepartment;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployeeDepartment implements IManageEmployeeDepartment {
	
	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();
	@Override
	public int addEmployeeDepartment(String token, String department) {
		try{
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				EmployeeDepartment empType = getEmployeeDepartmentByName(token, department);
				if(empType == null) {
					PreparedStatement insert = connection.
							prepareStatement(HRConstants.INSERT_EMP_DEPARTMENT);
					insert.setString(0, department);
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
	public int removeEmployeeDepartment(String token, String department) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_EMP_DEPARTMENT);
				delete.setString(0, department);
				int result = delete.executeUpdate();
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
	public EmployeeDepartment getEmployeeDepartmentByName(String token, String department) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_EMP_DEPARTMENT_BY_NAME);
				select.setString(0, department);
				ResultSet result = select.executeQuery();
				if(result.next()) {
					EmployeeDepartment empDep = new EmployeeDepartment(
							result.getInt(HRConstants.DEPARTMENT_ID),
							result.getString(HRConstants.DEPARTMENT));
					return empDep;
				} else {
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
	public List<EmployeeDepartment> getAllEmployeeDepartments(String token) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_ALL_EMP_DEPARTMENT);
				ResultSet result = select.executeQuery();
				List<EmployeeDepartment> list = generateEmpDepartmentList(result);
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

	private List<EmployeeDepartment> generateEmpDepartmentList(ResultSet result)
			throws SQLException {
		List<EmployeeDepartment> list = new ArrayList<EmployeeDepartment>();
		while(result.next()) {
			EmployeeDepartment empType = new EmployeeDepartment(
					result.getInt(HRConstants.DEPARTMENT_ID),
					result.getString(HRConstants.DEPARTMENT));
			list.add(empType);
		}
		return list;
	}

}
