/**
 * 
 */
package ws.security;

import org.junit.Test;

import ws.security.Impl.InputValidationManagerImpl;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class InputValidationTests extends TestCase {
	
	private InputValidationManager manager = new InputValidationManagerImpl();
	
	@Test
	public void testTextInputValidation() {
		assertTrue(manager.textValidation("asdAz !'\"$%@^&*()_-=+091#~;.,"));
		assertTrue(manager.textValidation("kjghj njaskfah kakjfs jaka"));
		assertFalse(manager.textValidation("<script>"));
		assertFalse(manager.textValidation(""));
	}
	@Test
	public void testUsernameValidation() {
		assertTrue(manager.usernameValidation("jgbhaknb"));
		assertFalse(manager.usernameValidation("AJDN la u1"));
		assertFalse(manager.usernameValidation("SK"));
		assertFalse(manager.usernameValidation("123"));
		assertFalse(manager.usernameValidation(""));
	}
	@Test
	public void testPasswordValidation() {
		assertFalse(manager.passwordValidation(""));
		assertFalse(manager.passwordValidation("sad "));
		assertFalse(manager.passwordValidation("^asdkj_"));
		assertTrue(manager.passwordValidation("asdAsd@#$%&_091"));
	}
	@Test
	public void testEmailValidation() {
		assertTrue(manager.emailValidation("test12_a@ga.com"));
		assertFalse(manager.emailValidation("@a.com"));
		assertFalse(manager.emailValidation(""));
		assertFalse(manager.emailValidation("dsadsd13"));
		assertFalse(manager.emailValidation("asd@sda"));
		assertFalse(manager.emailValidation("1asddas.com"));
	}
	
}
