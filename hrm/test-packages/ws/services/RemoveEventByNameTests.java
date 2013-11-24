/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.RemoveEventByName;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class RemoveEventByNameTests extends TestCase {
	private IRemoveEventByName removeEvent = new RemoveEventByName();
	private final String eventName = "testEvent";
	private final String nonExistentEvent = "test";
	
	@Test
	public void testRemoveEventByNameSuccess() {
		String result = removeEvent.removeEventByName(eventName);
		assertEquals(HRConstants.DELETE_SUCCESS, result);
	}
	@Test
	public void testRemoveEventByNameFailed() {
		String result = removeEvent.removeEventByName(nonExistentEvent);
		assertEquals(HRConstants.EVENT_DOES_NOT_EXIST, result);
	}
}
