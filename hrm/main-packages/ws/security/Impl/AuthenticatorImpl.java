/**
 * 
 */
package ws.security.Impl;

import ws.security.Authenticator;

/**
 * @author Kristiyan
 *
 */
public class AuthenticatorImpl implements Authenticator {
	
	//temp!!
	private static String u = "test";
	private static String p = "test";
	
	/* (non-Javadoc)
	 * @see ws.security.Authenticator#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authenticate(final String username, final String password) {
		if(username.equals(u) && password.equals(p)) {
			return true;
		} else {
			return false;
		}
	}

}
