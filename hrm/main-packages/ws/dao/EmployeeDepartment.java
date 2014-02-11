/**
 * 
 */
package ws.dao;

/**
 * @author Kristiyan
 *
 */
public class EmployeeDepartment {
	private int departmentId;
	private String department;
	
	public EmployeeDepartment() {}

	/**
	 * @param departmentId
	 * @param department
	 */
	public EmployeeDepartment(int departmentId, String department) {
		this.departmentId = departmentId;
		this.department = department;
	}

	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeDepartment [departmentId=" + departmentId
				+ ", department=" + department + "]";
	}

	
	
}
