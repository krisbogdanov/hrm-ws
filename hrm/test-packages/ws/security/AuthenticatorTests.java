/**
 * 
 */
package ws.security;

import ws.security.Impl.AuthenticatorImpl;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class AuthenticatorTests extends TestCase {
	
	private final Authenticator auth = new AuthenticatorImpl();
	
	public void testAuthenticateNotNull() {
		assertNotNull(auth.authenticate(null, null));
	}
	public void testAuthenticateEqualsFalse() {
		assertEquals(false, auth.authenticate(null, null));
	}
	public void testAuthenticateAssertTrue() {
		assertTrue(auth.authenticate("test", "test"));
	}
	public void testAuthenticateWithWrongUser() {
		assertEquals(false, auth.authenticate("wrong", "test"));
	}
	public void testAuthenticateWithWrongPass() {
		assertEquals(false, auth.authenticate("test", "wrong"));
	}
}
