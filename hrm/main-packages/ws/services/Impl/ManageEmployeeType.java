/**
 * 
 */
package ws.services.Impl;

import java.util.List;

import ws.dao.EmployeeType;
import ws.services.IManageEmployeeType;

/**
 * @author Kristiyan
 *
 */
public class ManageEmployeeType implements IManageEmployeeType {

	/* (non-Javadoc)
	 * @see ws.services.IManageEmployeeType#addEmployeeType(java.lang.String, java.lang.String)
	 */
	@Override
	public int addEmployeeType(String token, String type) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEmployeeType#removeEmployeeType(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeEmployeeType(String token, String type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EmployeeType getEmployeeTypeIdByName(String token, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeType> getAllEmployeeTypes(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
