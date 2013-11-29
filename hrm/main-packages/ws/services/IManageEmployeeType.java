/**
 * 
 */
package ws.services;

import java.util.List;

import ws.dao.EmployeeType;

/**
 * @author Kristiyan
 *
 */
public interface IManageEmployeeType {
	public int addEmployeeType(final String token, final String type);
	public int removeEmployeeType(final String token, final String type);
	public EmployeeType getEmployeeTypeIdByName(final String token, final String type);
	public List<EmployeeType> getAllEmployeeTypes(final String token);
}
