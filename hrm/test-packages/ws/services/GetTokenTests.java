/**
 * 
 */
package ws.services;

import ws.services.Impl.GetTokenImpl;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class GetTokenTests extends TestCase {
	
	public void testGetToken() {
		GetToken tokenGranter = new GetTokenImpl();
		assertNull(tokenGranter.getToken(null, null));
		assertNotNull(tokenGranter.getToken("test", "test"));
	}
}
