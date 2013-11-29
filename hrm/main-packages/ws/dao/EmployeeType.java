/**
 * 
 */
package ws.dao;

/**
 * @author Kristiyan
 *
 */
public class EmployeeType {
	private int typeId;
	private String typeOfEmployee;
	
	public EmployeeType() {}

	/**
	 * @param typeId
	 * @param typeOfEmployee
	 */
	public EmployeeType(int typeId, String typeOfEmployee) {
		this.typeId = typeId;
		this.typeOfEmployee = typeOfEmployee;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeOfEmployee() {
		return typeOfEmployee;
	}

	public void setTypeOfEmployee(String typeOfEmployee) {
		this.typeOfEmployee = typeOfEmployee;
	}
	
}
