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
import ws.services.IManageEmployeeDepartment;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployeeDepartment implements IManageEmployeeDepartment {
	
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IManageEmployeeType#addEmployeeType(java.lang.String, java.lang.String)
	 */
	@Override
	public int addEmployeeDepartment(String token, String department) {
		try{
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
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEmployeeType#removeEmployeeType(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeEmployeeDepartment(String token, String department) {
		try{
			PreparedStatement delete = connection.
					prepareStatement(HRConstants.DELETE_EMP_DEPARTMENT);
			delete.setString(0, department);
			int result = delete.executeUpdate();
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

	@Override
	public EmployeeDepartment getEmployeeDepartmentByName(String token, String department) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_EMP_DEPARTMENT_BY_NAME);
			select.setString(0, department);
			ResultSet result = select.executeQuery();
			select.close();
			if(result.next()) {
				EmployeeDepartment empType = new EmployeeDepartment(
						result.getInt(HRConstants.DEPARTMENT_ID),
						result.getString(HRConstants.DEPARTMENT));
				return empType;
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
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_ALL_EMP_DEPARTMENT);
			ResultSet result = select.executeQuery();
			List<EmployeeDepartment> list = generateEmpDepartmentList(result);
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
