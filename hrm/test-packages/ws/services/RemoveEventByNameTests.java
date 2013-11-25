/**
 * 
 */
package ws.services;

import org.junit.Test;

import ws.services.Impl.RemoveEventByName;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
/**
 * @author Kristiyan
 *
 */
public class RemoveEventByNameTests extends TestCase {
	private IRemoveEventByName removeEvent = mock(RemoveEventByName.class);
	private final String eventName = "testEvent";
	private final String nonExistentEvent = "test";
	
	@Test
	public void testRemoveEventByNameSuccess() {
		when(removeEvent.removeEventByName(eventName))
		.thenReturn(HRConstants.DELETE_SUCCESS);
	}
	@Test
	public void testRemoveEventByNameFailed() {
		when(removeEvent.removeEventByName(nonExistentEvent))
		.thenReturn(HRConstants.EVENT_DOES_NOT_EXIST);
	}
}
