/**
 * 
 */
package ws.services.Impl;

import java.util.List;


import ws.dao.Employee;
import ws.services.IManageEmployee;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployee implements IManageEmployee {
    

	@Override
	public int addEmployee(String token, String employeeName,
			String employeeSurname, String employeeEmail,
			String employeeAddress, String employeeSSN, String employeePhone,
			int employeeType) {
		// TODO Auto-generated method stub
		if(employeeType == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int removeEmployeeById(String token, int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editEmployeeById(String token, int employeeId,
			String employeeName, String employeeSurname, String employeeEmail,
			String employeeAddress, String employeeSSN, String employeePhone,
			int employeeType, String employeeUsername) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeEmployeePassword(String token, int employeeId,
			String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee getEmployeeById(String token, int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchEmployeeByName(String token, String searchPhrase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int RegisterEmployeeForGradTraining(String token, int employeeId,
			int gradTrainingId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int UnregisterEmployeeFromGradTraining(String token, int employeeId,
			int gradTrainingId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addBankDetails(String token, int employeeId, String bankName,
			int accountNumber, int sortCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBankDetailsByEmployeeId(String token, int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editBankDetailsByEmployeeId(String token, int employeeId,
			String bankName, int accountNumber, int sortCode) {
		// TODO Auto-generated method stub
		return 0;
	}


}
