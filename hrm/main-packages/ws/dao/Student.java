/**
 * 
 */
package ws.dao;

import java.util.Date;

/**
 * @author Kristiyan
 *
 */
public class Student {
	private int studentId;
	private String studentName;
	private String studentSurname;
	private String studentEmail;
	private Date studentRegistered;
	
	public Student() {}

	/**
	 * @param studentId
	 * @param studentName
	 * @param studentSurname
	 * @param studentEmail
	 * @param studentRegistered
	 */
	public Student(int studentId, String studentName, String studentSurname,
			String studentEmail, Date studentRegistered) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentSurname = studentSurname;
		this.studentEmail = studentEmail;
		this.studentRegistered = studentRegistered;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSurname() {
		return studentSurname;
	}

	public void setStudentSurname(String studentSurname) {
		this.studentSurname = studentSurname;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public Date getStudentRegistered() {
		return studentRegistered;
	}

	public void setStudentRegistered(Date studentRegistered) {
		this.studentRegistered = studentRegistered;
	}
	
}
