/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.GetToken;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class GetTokenTests extends TestCase {
	private final IGetToken tokenGranter = new GetToken();
	private final String testUser = "test";
	private final String testPassword = "test";
	@Test
	public void testGetTokenEqualsAccessDenied() {
		assertEquals(HRConstants.ACCESS_DENIED, tokenGranter.getToken(null, null));
	}
	@Test
	public void testGetTokenNotNull() {
		assertNotNull(tokenGranter.getToken(testUser, testPassword));
	}
	
}
