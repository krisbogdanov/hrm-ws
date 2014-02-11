/**
 * 
 */
package ws.services.Impl;

import java.util.UUID;

import ws.security.AuthenticationManager;
import ws.security.Impl.AuthenticationManagerImpl;
import ws.services.IGetToken;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class GetToken implements IGetToken {
	
	
	private final AuthenticationManager authenticator = new AuthenticationManagerImpl();
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
			return HRConstants.ACCESS_DENIED;
		}
	}
}
