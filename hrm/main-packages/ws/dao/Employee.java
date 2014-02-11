/**
 * 
 */
package ws.dao;

import java.util.Date;

/**
 * @author Kristiyan
 *
 */
public class Employee {
	private int employeeId;
	private String employeeName;
	private String employeeSurname;
	private String employeeEmail;
	private String employeeAddress;
	private String employeeSSN;
	private String employeeUsername;
	private String employeePassword;
	private Date employeeJoined;
	private int employeeDepartment;
	private String employeeRole;
	private String employeePhone;
	
	public Employee() {}

	/**
	 * @param employeeId
	 * @param employeeName
	 * @param employeeSurname
	 * @param employeeEmail
	 * @param employeeAddress
	 * @param employeeSSN
	 * @param employeeUsername
	 * @param employeePassword
	 * @param employeeJoined
	 * @param employeeDepartment
	 * @param employeePhone
	 */
	public Employee(int employeeId, String employeeName,
			String employeeSurname, String employeeEmail,
			String employeeAddress, String employeeSSN,
			String employeeUsername, String employeePassword,
			Date employeeJoined, int employeeDepartment, String employeePhone, String employeeRole) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
		this.employeeSSN = employeeSSN;
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.employeeJoined = employeeJoined;
		this.employeeDepartment = employeeDepartment;
		this.employeePhone = employeePhone;
		this.employeeRole = employeeRole;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSurname() {
		return employeeSurname;
	}

	public void setEmployeeSurname(String employeeSurname) {
		this.employeeSurname = employeeSurname;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeSSN() {
		return employeeSSN;
	}

	public void setEmployeeSSN(String employeeSSN) {
		this.employeeSSN = employeeSSN;
	}

	public String getEmployeeUsername() {
		return employeeUsername;
	}

	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public Date getEmployeeJoined() {
		return employeeJoined;
	}

	public void setEmployeeJoined(Date employeeJoined) {
		this.employeeJoined = employeeJoined;
	}

	public int getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(int employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", employeeSurname=" + employeeSurname
				+ ", employeeEmail=" + employeeEmail + ", employeeAddress="
				+ employeeAddress + ", employeeSSN=" + employeeSSN
				+ ", employeeUsername=" + employeeUsername
				+ ", employeePassword=" + employeePassword
				+ ", employeeJoined=" + employeeJoined
				+ ", employeeDepartment=" + employeeDepartment
				+ ", employeeRole=" + employeeRole + ", employeePhone="
				+ employeePhone + "]";
	}
	
}
