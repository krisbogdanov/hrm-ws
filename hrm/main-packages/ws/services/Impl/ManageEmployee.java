/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import ws.dao.BankDetails;
import ws.dao.Employee;
import ws.security.AuthorizationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.services.IManageEmployee;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployee implements IManageEmployee {

	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();

	@Override
	public int addEmployee(String token, String employeeName,
			String employeeSurname, String employeeEmail,
			String employeeAddress, String employeeSSN, String employeePhone,
			int employeeDepartment, String employeeRole, String employeeUsername) {
		try {
			
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				Employee employee = getEmployeeByUsername(token, employeeUsername);
				if(employee == null) {
					Date employeeJoined = new Date();
					PreparedStatement insert = connection.
							prepareStatement(HRConstants.INSERT_EMPLOYEE);
					insert.setString(1, employeeName);
					insert.setString(2, employeeSurname);
					insert.setString(3, employeeEmail);
					insert.setString(4, employeeAddress);
					insert.setString(5, employeeSSN);
					insert.setString(6, employeePhone);
					insert.setObject(7, employeeJoined);
					insert.setInt(8, employeeDepartment);
					insert.setString(9, employeeRole);
					insert.setString(10, employeeUsername);
					insert.setString(11, HRConstants.TEMP_PASSWORD);
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
	public int removeEmployeeById(String token, int employeeId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_EMPLOYEE_BY_ID);
				delete.setInt(1, employeeId);
				Integer result = delete.executeUpdate();
				PreparedStatement deleteMappings = connection.
						prepareStatement(HRConstants.REMOVE_GRAD_TRAINING_MAPPINGS_FOR_EMP);
				deleteMappings.setInt(1, employeeId);
				deleteMappings.executeUpdate();
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
	public int editEmployeeById(String token, int employeeId,
			String employeeName, String employeeSurname, String employeeEmail,
			String employeeAddress, String employeeSSN, String employeePhone,
			int employeeDepartment, String employeeUsername, String employeeRole) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) 
					|| authManager.isTheOwnerOf(token, employeeId)) {
				PreparedStatement update = connection.
						prepareStatement(HRConstants.UPDATE_EMPLOYEE_BY_ID);
				update.setString(1, employeeName);
				update.setString(2, employeeSurname);
				update.setString(3, employeeEmail);
				update.setString(4, employeeAddress);
				update.setString(5, employeeSSN);
				update.setString(6, employeePhone);
				update.setInt(7, employeeDepartment);
				update.setString(8, employeeUsername);
				update.setString(9, employeeRole);
				update.setInt(10, employeeId);
				int result = update.executeUpdate();
				update.close();
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
	public int changeEmployeePassword(String token, int employeeId,
			String oldPassword, String newPassword) {
		try {
			if(authManager.isTheOwnerOf(token, employeeId)) {
				Employee emp = getEmployeeById(token, employeeId);
				if(emp != null) {
					if(emp.getEmployeePassword().equals(oldPassword)) {
						PreparedStatement update = connection.
								prepareStatement(HRConstants.UPDATE_EMPLOYEE_PASSWORD);
						update.setString(1, newPassword);
						update.setInt(2, employeeId);
						int result = update.executeUpdate();
						update.close();
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
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Employee getEmployeeByUsername(String token, String employeeUsername) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_EMPLOYEE_BY_USERNAME);
				select.setString(1, employeeUsername);
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
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> searchEmployeeByName(String token, String searchPhrase, boolean secure) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				String sql = "SELECT * FROM hr.Employee WHERE employeeName LIKE '%"+searchPhrase+"%' " +
						"OR employeeSurname LIKE '%"+searchPhrase+"%';"; //very wrong
				PreparedStatement select = null;
				if(secure) {
					select = connection.prepareStatement(HRConstants.SEARCH_EMPLOYEE_BY_NAME);
					select.setString(1, searchPhrase);
					select.setString(2, searchPhrase);
				} else {
					select = connection.prepareStatement(sql);
				}
				ResultSet result = select.executeQuery();
				List<Employee> list = generateEmployeeList(result);
				select.close();
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

	@Override
	public List<Employee> getAllEmployees(String token) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_ALL_EMPLOYEES);
				ResultSet result = select.executeQuery();
				List<Employee> list = generateEmployeeList(result); 
				select.close();
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

	private List<Employee> generateEmployeeList(ResultSet result)
			throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		while(result.next()) {
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
			list.add(employee);
		}
		return list;
	}
	@Override
	public int registerEmployeeForGradTraining(String token, int gradTrainingId,
			int employeeId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				if(checkGradTrainingRegistration(employeeId, gradTrainingId) > 0) {
					return 0;
				} else {
					PreparedStatement register = connection.
							prepareStatement(HRConstants.REGISTER_EMPLOYEE_TO_GRAD_TRAINING);
					register.setInt(1, gradTrainingId);
					register.setInt(2, employeeId);
					int result = register.executeUpdate();
					register.close();
					if(result == 0) {
						return 0;
					} else {
						return 1;
					}
				}
			} else {
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	private int checkGradTrainingRegistration(int employeeId, int gradTrainingId) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_EMPLOYEE_TO_GRAD_TRAINING);
			select.setInt(1, employeeId);
			select.setInt(2, gradTrainingId);
			ResultSet result = select.executeQuery();
			if(result.next()) {
				select.close();
				return 1;
			} else {
				select.close();
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int unregisterEmployeeFromGradTraining(String token, int gradTrainingId,
			int employeeId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.UNREGISTER_EMPLOYEE_FROM_GRAD_TRAINING);
				delete.setInt(1, employeeId);
				delete.setInt(2, gradTrainingId);
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
	public int addBankDetails(String token, int employeeId, String bankName,
			int accountNumber, int sortCode) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) ||
					authManager.isTheOwnerOf(token, employeeId)) {
				BankDetails details = getBankDetailsByEmployeeId(token, employeeId);
				if(details == null) {
					PreparedStatement insert = connection.
							prepareStatement(HRConstants.INSERT_BANK_DETAILS);
					insert.setInt(1, employeeId);
					insert.setString(2, bankName);
					insert.setInt(3, accountNumber);
					insert.setInt(4, sortCode);
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
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeBankDetailsByEmployeeId(String token, int employeeId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) ||
					authManager.isTheOwnerOf(token, employeeId)) {
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_BANK_DETAILS_BY_EMPLOYEE_ID);
				delete.setInt(1, employeeId);
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
	public int editBankDetailsByEmployeeId(String token, int employeeId,
			String bankName, int accountNumber, int sortCode) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) ||
					authManager.isTheOwnerOf(token, employeeId)) {
				PreparedStatement update = connection.
						prepareStatement(HRConstants.UPDATE_BANK_DETAILS_BY_EMPLOYEE_ID);
				update.setString(1, bankName);
				update.setInt(2, accountNumber);
				update.setInt(3, sortCode);
				update.setInt(4, employeeId);
				int result = update.executeUpdate();
				update.close();
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
	public BankDetails getBankDetailsByEmployeeId(String token, int employeeId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) ||
					authManager.isTheOwnerOf(token, employeeId)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_BANK_DETAILS_BY_EMP_ID);
				select.setInt(1, employeeId);
				ResultSet result = select.executeQuery();
				if(result.next()) {
					BankDetails bankDetails = new BankDetails(
							result.getInt(HRConstants.BANK_DETAILS_ID), 
							result.getInt(HRConstants.EMPLOYEE_ID), 
							result.getString(HRConstants.BANK_NAME), 
							result.getInt(HRConstants.ACCOUNT_NUMBER), 
						    result.getInt(HRConstants.SORT_CODE));
					select.close();
					return bankDetails;
				} else {
					select.close();
					return null;	
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee getEmployeeById(String token, int employeeId) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_EMPLOYEE_BY_ID);
				select.setInt(1, employeeId);
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
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


}
