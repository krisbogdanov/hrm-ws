/**
 * 
 */
package ws.services;

import java.util.Date;

import org.junit.Test;

import ws.services.Impl.AddEvent;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
/**
 * @author Kristiyan
 *
 */
public class AddEventTests extends TestCase {
	
	private IAddEvent addEvent = mock(AddEvent.class);
	private final String eventName = "testEvent";
	private final String eventLocation = "test Location";
	private final Date eventDate = new Date();
	private final int eventDurationInMinutes = 120;
	private final int eventCapacity = 80;
	/**
	 * This test should fail if the test is run more than once.
	 * Meaning that the test event will already be added so only the second
	 * test will succeed unless the event is deleted from the database.
	 */
	@Test
	public void testAddEventEqualsSuccess() {
		when(addEvent.addEvent(eventName, eventLocation,
				eventDate, eventDurationInMinutes, eventCapacity))
				.thenReturn(HRConstants.INSERT_SUCCESS);
		
	}
	@Test
	public void testAddEventEqualsEventAlreadyAdded() {
		when(addEvent.addEvent(eventName, eventLocation,
				eventDate, eventDurationInMinutes, eventCapacity))
		.thenReturn(HRConstants.EVENT_ALREADY_ADDED);
	}
}
