/**
 * 
 */
package ws.services;

import ws.services.Impl.GetToken;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class GetTokenTests extends TestCase {
	private final IGetToken tokenGranter = new GetToken();
	public void testGetTokenNull() {
		assertNull(tokenGranter.getToken(null, null));
	}
	public void testGetTokenNotNull() {
		assertNotNull(tokenGranter.getToken("test", "test"));
	}
	
}
