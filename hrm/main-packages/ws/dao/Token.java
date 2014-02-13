/**
 * 
 */
package ws.dao;

import java.util.Date;

/**
 * @author Kristiyan
 *
 */
public class Token {
	private String token;
	private int employeeId;
	private Date tokenExpire;
	private int permissions;
	
	public Token() {}
	/**
	 * @param token
	 * @param employeeId
	 * @param tokenExpire
	 * @param permissions
	 */
	public Token(String token, int employeeId, Date tokenExpire, int permissions) {
		this.token = token;
		this.employeeId = employeeId;
		this.tokenExpire = tokenExpire;
		this.permissions = permissions;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the tokenExpire
	 */
	public Date getTokenExpire() {
		return tokenExpire;
	}
	/**
	 * @param tokenExpire the tokenExpire to set
	 */
	public void setTokenExpire(Date tokenExpire) {
		this.tokenExpire = tokenExpire;
	}
	/**
	 * @return the permissions
	 */
	public int getPermissions() {
		return permissions;
	}
	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	
	
}
