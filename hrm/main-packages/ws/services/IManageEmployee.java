/**
 * 
 */
package ws.services;

import java.util.List;

import ws.dao.BankDetails;
import ws.dao.Employee;

/**
 * @author Kristiyan
 *
 */
public interface IManageEmployee {
	
	public int addEmployee(final String token, final String employeeName, final String employeeSurname,
							final String employeeEmail, final String employeeAddress,
							final String employeeSSN, final String employeePhone,
							final int employeeDepartment, final String employeeRole, final String employeeUsername);
	
	public int removeEmployeeById(final String token, final int employeeId);
	
	public int editEmployeeById(final String token, final int employeeId, 
			final String employeeName, final String employeeSurname,
			final String employeeEmail, final String employeeAddress,
			final String employeeSSN, final String employeePhone,
			final int employeeDepartment, final String employeeRole, final String employeeUsername);
	
	public int changeEmployeePassword(final String token, final int employeeId,
			final String oldPassword, final String newPassword); 
	
	public Employee getEmployeeByUsername(final String token, final String employeeUsername);
	public Employee getEmployeeById(final String token, final int employeeId);
	public List<Employee> searchEmployeeByName(final String token, final String searchPhrase, boolean secure);
	
	public List<Employee> getAllEmployees(final String token);
	
	public int registerEmployeeForGradTraining(final String token, final int gradTrainingId,
			final int employeeId);
	public int unregisterEmployeeFromGradTraining(final String token, final int gradTrainingId,
			final int employeeId);
	public int addBankDetails(final String token, final int employeeId, final String bankName,
			final int accountNumber, final int sortCode);
	public int removeBankDetailsByEmployeeId(final String token, final int employeeId);
	public int editBankDetailsByEmployeeId(final String token, final int employeeId, final String bankName,
			final int accountNumber, final int sortCode);
	public BankDetails getBankDetailsByEmployeeId(final String token, final int employeeId);
}
