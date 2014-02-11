/**
 * 
 */
package ws.security;

import org.junit.Test;

import ws.dao.Employee;
import ws.security.Impl.AuthenticationManagerImpl;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class AuthenticatorTests extends TestCase {
	
	private final AuthenticationManager auth = new AuthenticationManagerImpl();
	private final String testUser = "saraf";
	private final String testPassword = "mypass";
	@Test
	public void testAuthenticateSuccess() {
		Employee emp = auth.authenticate(testUser, testPassword);
		assertNotNull(emp);
	}
	@Test
	public void testAuthenticateFail() {
		Employee emp = auth.authenticate("saaraaf", testPassword);
		assertNull(emp);
	}
}
