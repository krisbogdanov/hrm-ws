/**
 * 
 */
package ws.services;

import java.util.List;

import ws.dao.EmployeeDepartment;

/**
 * @author Kristiyan
 *
 */
public interface IManageEmployeeDepartment {
	public int addEmployeeDepartment(final String token, final String department);
	public int removeEmployeeDepartment(final String token, final String department);
	public EmployeeDepartment getEmployeeDepartmentByName(final String token, final String department);
	public List<EmployeeDepartment> getAllEmployeeDepartments(final String token);
}
