/**
 * 
 */
package ws.services;

import java.util.List;

import ws.dao.EmployeePerformance;

/**
 * @author Kristiyan
 *
 */
public interface IManageEmployeePerformance {
	public int addEmployeePerformance(final String token, final int employeeId,
			final String perfDescription, final int year);
	/**
	 * If you pass null here to any of the editable fields (perfDescription, year)
	 * it won't be updated.
	 * @param token
	 * @param employeeId
	 * @param perfDescription
	 * @param year
	 * @return
	 */
	public int editEmployeePerformance(final String token, final int perfId,
			final String perfDescription);
	
	public int removeEmployeePerformanceByPerfId(final String token, final int perfId);
	
	public List<EmployeePerformance> getEmployeePerformanceByEmployeeId(final String token,
			final int employeeId);
}
