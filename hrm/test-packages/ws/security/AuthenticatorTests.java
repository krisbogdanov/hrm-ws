/**
 * 
 */
package ws.security;

import ws.security.Impl.AuthenticationManagerImpl;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class AuthenticatorTests extends TestCase {
	
	private final AuthenticationManager auth = new AuthenticationManagerImpl();
	
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
