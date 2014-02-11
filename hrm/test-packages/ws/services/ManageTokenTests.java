/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.ManageToken;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class ManageTokenTests extends TestCase {
	private final IManageToken tokenGranter = new ManageToken();
	private final String testUser = "saraf";
	private final String testPassword = "mypass";
	@Test
	public void testGetTokenEqualsAccessDenied() {
		assertEquals(HRConstants.ACCESS_DENIED, tokenGranter.getToken(null, null));
		assertEquals(HRConstants.ACCESS_DENIED, tokenGranter.getToken("saraf", "12sdsasf2134"));
	}
	@Test
	public void testGetTokenThenDelete() {
		String token = tokenGranter.getToken(testUser, testPassword);
		assertNotNull(token);
		System.out.println("Token: " + token);
		tokenGranter.deleteToken(token);
	}
}
