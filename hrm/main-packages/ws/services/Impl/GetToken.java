/**
 * 
 */
package ws.services.Impl;

import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ws.security.Authenticator;
import ws.security.Impl.AuthenticatorImpl;
import ws.services.IGetToken;

/**
 * @author Kristiyan
 *
 */
@WebService(targetNamespace = "http://Impl.services.ws/", portName = "GetTokenPort", serviceName = "GetTokenService")
public class GetToken implements IGetToken {
	
	private final Authenticator authenticator = new AuthenticatorImpl();
	/* (non-Javadoc)
	 * @see ws.services.GetToken#getToken(java.lang.String, java.lang.String)
	 */
	@WebMethod(operationName = "getToken", action = "urn:GetToken")
	@RequestWrapper(className = "ws.services.Impl.jaxws.GetToken", localName = "getToken", targetNamespace = "http://Impl.services.ws/")
	@ResponseWrapper(className = "ws.services.Impl.jaxws.GetTokenResponse", localName = "getTokenResponse", targetNamespace = "http://Impl.services.ws/")
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