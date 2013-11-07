/**
 * 
 */
package ws.services.Impl;

import java.util.UUID;

import ws.security.Authenticator;
import ws.security.Impl.AuthenticatorImpl;
import ws.services.IGetToken;

/**
 * @author Kristiyan
 *
 */
public class GetToken implements IGetToken {
	
	private final Authenticator authenticator = new AuthenticatorImpl();
	/* (non-Javadoc)
	 * @see ws.services.GetToken#getToken(java.lang.String, java.lang.String)
	 */
	@Override
	public String getToken(final String username, final String password) {
		return generateToken(username, password);
	}
	private String generateToken(final String username, final String password) {
		if(authenticator.authenticate(username, password)) {
			return UUID.randomUUID().toString().toUpperCase();
		} else {
			return "Access Denied!";
		}
	}
}
