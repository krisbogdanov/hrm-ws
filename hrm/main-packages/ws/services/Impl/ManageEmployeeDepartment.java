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

import ws.dao.EmployeeDepartment;
import ws.security.AuthorizationManager;
import ws.security.InputValidationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.security.Impl.InputValidationManagerImpl;
import ws.services.IManageEmployeeDepartment;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
@WebService(targetNamespace = "http://Impl.services.ws/", portName = "ManageEmployeeDepartmentPort", serviceName = "ManageEmployeeDepartmentService")
public class ManageEmployeeDepartment implements IManageEmployeeDepartment {
	
	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();
	private final InputValidationManager validationManager = new InputValidationManagerImpl();
	@Override
	public int addEmployeeDepartment(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String department, @WebParam(name = "arg2") boolean secure) {
		try{
			if(secure) {
				if(!validationManager.textValidation(department)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				EmployeeDepartment empType = getEmployeeDepartmentByName(token, department, secure);
				if(empType == null) {
					PreparedStatement insert = connection.
							prepareStatement(HRConstants.INSERT_EMP_DEPARTMENT);
					insert.setString(1, department);
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
	public int removeEmployeeDepartment(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String department, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(department)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_EMP_DEPARTMENT);
				delete.setString(1, department);
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
	public EmployeeDepartment getEmployeeDepartmentByName(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String department, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(department)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_EMP_DEPARTMENT_BY_NAME);
				select.setString(1, department);
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
	public List<EmployeeDepartment> getAllEmployeeDepartments(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") boolean secure) {
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
			EmployeeDepartment empDep = new EmployeeDepartment(
					result.getInt(HRConstants.DEPARTMENT_ID),
					result.getString(HRConstants.DEPARTMENT));
			list.add(empDep);
		}
		return list;
	}

}
