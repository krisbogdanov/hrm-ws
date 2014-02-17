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
			final String perfDescription, final int year, boolean secure);
	public int editEmployeePerformance(final String token, final int perfId,
			final String perfDescription, boolean secure);
	
	public int removeEmployeePerformanceByPerfId(final String token, final int perfId, boolean secure);
	
	public List<EmployeePerformance> getEmployeePerformanceByEmployeeId(final String token,
			final int employeeId, boolean secure);
}
