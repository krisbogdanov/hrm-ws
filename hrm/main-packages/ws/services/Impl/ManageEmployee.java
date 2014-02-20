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

import javax.jws.WebParam;
import javax.jws.WebService;


import ws.dao.BankDetails;
import ws.dao.Employee;
import ws.security.AuthorizationManager;
import ws.security.InputValidationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.security.Impl.InputValidationManagerImpl;
import ws.services.IManageEmployee;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
@WebService(targetNamespace = "http://Impl.services.ws/", portName = "ManageEmployeePort", serviceName = "ManageEmployeeService")
public class ManageEmployee implements IManageEmployee {

	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();
	private final InputValidationManager validationManager = new InputValidationManagerImpl();
	@Override
	public int addEmployee(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String employeeName,
			@WebParam(name = "arg2") String employeeSurname, @WebParam(name = "arg3") String employeeEmail,
			@WebParam(name = "arg4") String employeeAddress, @WebParam(name = "arg5") String employeeSSN, @WebParam(name = "arg6") String employeePhone,
			@WebParam(name = "arg7") int employeeDepartment, @WebParam(name = "arg8") String employeeRole, @WebParam(name = "arg9") String employeeUsername, @WebParam(name = "arg10") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(employeeName) || 
						!validationManager.textValidation(employeeSurname) ||
						!validationManager.emailValidation(employeeEmail) ||
						!validationManager.textValidation(employeeAddress) ||
						!validationManager.textValidation(employeeSSN) ||
						!validationManager.textValidation(employeePhone) ||
						!validationManager.integerValidation(employeeDepartment) ||
						!validationManager.textValidation(employeeRole) ||
						!validationManager.usernameValidation(employeeUsername)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				Employee employee = getEmployeeByUsername(token, employeeUsername, secure);
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
	public int removeEmployeeById(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId)) {
					return 0;
				}
			}
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
	public int editEmployeeById(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId,
			@WebParam(name = "arg2") String employeeName, @WebParam(name = "arg3") String employeeSurname, @WebParam(name = "arg4") String employeeEmail,
			@WebParam(name = "arg5") String employeeAddress, @WebParam(name = "arg6") String employeeSSN, @WebParam(name = "arg7") String employeePhone,
			@WebParam(name = "arg8") int employeeDepartment, @WebParam(name = "arg9") String employeeUsername, @WebParam(name = "arg10") String employeeRole, @WebParam(name = "arg11") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(employeeName) || 
						!validationManager.textValidation(employeeSurname) ||
						!validationManager.emailValidation(employeeEmail) ||
						!validationManager.textValidation(employeeAddress) ||
						!validationManager.textValidation(employeeSSN) ||
						!validationManager.textValidation(employeePhone) ||
						!validationManager.integerValidation(employeeDepartment) ||
						!validationManager.textValidation(employeeRole) ||
						!validationManager.usernameValidation(employeeUsername) ||
						!validationManager.integerValidation(employeeId)) {
					return 0;
				}
			}
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
	public int changeEmployeePassword(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId,
			@WebParam(name = "arg2") String oldPassword, @WebParam(name = "arg3") String newPassword, @WebParam(name = "arg4") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId) ||
						!validationManager.passwordValidation(oldPassword) ||
						!validationManager.passwordValidation(newPassword)) {
					return 0;
				}
			}
			if(authManager.isTheOwnerOf(token, employeeId)) {
				Employee emp = getEmployeeById(token, employeeId, secure);
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
	public Employee getEmployeeByUsername(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String employeeUsername, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.usernameValidation(employeeUsername)) {
					return null;
				}
			}
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
	public List<Employee> searchEmployeeByName(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String searchPhrase, @WebParam(name = "arg2") boolean secure) {
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
	public List<Employee> getAllEmployees(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") boolean secure) {
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
	public int registerEmployeeForGradTraining(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int gradTrainingId,
			@WebParam(name = "arg2") int employeeId, @WebParam(name = "arg3") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId) ||
						!validationManager.integerValidation(gradTrainingId)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				if(checkGradTrainingRegistration(employeeId, gradTrainingId, secure) > 0) {
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
	private int checkGradTrainingRegistration(int employeeId, int gradTrainingId, boolean secure) {
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
	public int unregisterEmployeeFromGradTraining(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int gradTrainingId,
			@WebParam(name = "arg2") int employeeId, @WebParam(name = "arg3") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId) ||
						!validationManager.integerValidation(gradTrainingId)) {
					return 0;
				}
			}
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
	public int addBankDetails(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId, @WebParam(name = "arg2") String bankName,
			@WebParam(name = "arg3") int accountNumber, @WebParam(name = "arg4") int sortCode, @WebParam(name = "arg5") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId) ||
						!validationManager.integerValidation(accountNumber) ||
						!validationManager.textValidation(bankName) ||
						!validationManager.integerValidation(sortCode)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE) ||
					authManager.isTheOwnerOf(token, employeeId)) {
				BankDetails details = getBankDetailsByEmployeeId(token, employeeId, secure);
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
	public int removeBankDetailsByEmployeeId(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId)) {
					return 0;
				}
			}
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
	public int editBankDetailsByEmployeeId(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId,
			@WebParam(name = "arg2") String bankName, @WebParam(name = "arg3") int accountNumber, @WebParam(name = "arg4") int sortCode, @WebParam(name = "arg5") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId) ||
						!validationManager.integerValidation(accountNumber) ||
						!validationManager.textValidation(bankName) ||
						!validationManager.integerValidation(sortCode)) {
					return 0;
				}
			}
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
	public BankDetails getBankDetailsByEmployeeId(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId)) {
					return null;
				}
			}
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
	public Employee getEmployeeById(@WebParam(name = "arg0") String token, @WebParam(name = "arg1") int employeeId, @WebParam(name = "arg2") boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(employeeId)) {
					return null;
				}
			}
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
					if(!authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
						if(!authManager.isTheOwnerOf(token, employee.getEmployeeId())) {
							employee.setEmployeePassword(HRConstants.EMPTY_STRING);
							employee.setEmployeeSSN(HRConstants.EMPTY_STRING);
							employee.setEmployeeUsername(HRConstants.EMPTY_STRING);
							employee.setEmployeeAddress(HRConstants.EMPTY_STRING);
						}
					}
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
