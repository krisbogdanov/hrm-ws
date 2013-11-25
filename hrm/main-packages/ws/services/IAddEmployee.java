/**
 * 
 */
package ws.services;

/**
 * @author Kristiyan
 *
 */
public interface IAddEmployee {
	
	public String addEmployee(final String employeeName, final String employeeSurname,
							final String employeeEmail, final String employeeAddress,
							final String employeeSSN, final String employeePhone,
							final int employeeType, final String employeeUsername,
							final String employeePassword);
}
