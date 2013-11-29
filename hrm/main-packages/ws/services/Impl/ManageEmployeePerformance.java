/**
 * 
 */
package ws.services.Impl;

import ws.dao.EmployeePerformance;
import ws.services.IManageEmployeePerformance;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployeePerformance implements IManageEmployeePerformance {

	/* (non-Javadoc)
	 * @see ws.services.IManagePerformance#addEmployeePerformance(java.lang.String, int, java.lang.String, int)
	 */
	@Override
	public int addEmployeePerformance(String token, int employeeId,
			String perfDescription, int year) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManagePerformance#editEmployeePerformance(java.lang.String, int, java.lang.String, int)
	 */
	@Override
	public int editEmployeePerformance(String token, int employeeId,
			String perfDescription, int year) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int removeEmployeePerformanceByEmployeeId(String token,
			int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EmployeePerformance getEmployeePerformanceByEmployeeId(String token,
			int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
