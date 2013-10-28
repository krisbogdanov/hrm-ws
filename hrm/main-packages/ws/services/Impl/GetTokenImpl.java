/**
 * 
 */
package ws.services.Impl;

import java.util.UUID;

import ws.services.GetToken;

/**
 * @author Kristiyan
 *
 */
public class GetTokenImpl implements GetToken {

	/* (non-Javadoc)
	 * @see ws.services.GetToken#getToken(java.lang.String, java.lang.String)
	 */
	@Override
	public String getToken(String username, String password) {
		return generateToken(username, password);
	}
	private String generateToken(String username, String password) {
		if(authenticate(username, password)) {
			return UUID.randomUUID().toString().toUpperCase();
		} else {
			return null;
		}
	}
	private boolean authenticate(String username, String password) {
		//TODO: implement it properly
		if(username == null || password == null) {
			return false;
		} else {
			return true;
		}
	}
}
