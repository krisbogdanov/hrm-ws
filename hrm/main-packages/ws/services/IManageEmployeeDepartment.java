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
	public int addEmployeeDepartment(final String token, final String department, boolean secure);
	public int removeEmployeeDepartment(final String token, final String department, boolean secure);
	public EmployeeDepartment getEmployeeDepartmentByName(final String token, final String department, boolean secure);
	public List<EmployeeDepartment> getAllEmployeeDepartments(final String token, boolean secure);
}
