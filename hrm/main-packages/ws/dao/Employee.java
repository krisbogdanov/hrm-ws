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
	private int employeeType;
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
	 * @param employeeType
	 * @param employeePhone
	 */
	public Employee(int employeeId, String employeeName,
			String employeeSurname, String employeeEmail,
			String employeeAddress, String employeeSSN,
			String employeeUsername, String employeePassword,
			Date employeeJoined, int employeeType, String employeePhone) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
		this.employeeSSN = employeeSSN;
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.employeeJoined = employeeJoined;
		this.employeeType = employeeType;
		this.employeePhone = employeePhone;
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

	public int getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	
}
