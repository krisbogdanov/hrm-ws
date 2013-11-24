/**
 * 
 */
package ws.services;

import java.util.Date;

import org.junit.Test;

import ws.services.Impl.AddEvent;
import ws.utils.Impl.HRConstants;
import junit.framework.TestCase;

/**
 * @author Kristiyan
 *
 */
public class AddEventTests extends TestCase {
	
	private IAddEvent addEvent = new AddEvent();
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
		String result = addEvent.addEvent(eventName, eventLocation,
				eventDate, eventDurationInMinutes, eventCapacity);
		assertEquals(HRConstants.INSERT_SUCCESS, result);
	}
	@Test
	public void testAddEventEqualsEventAlreadyAdded() {
		String result = addEvent.addEvent(eventName, eventLocation,
				eventDate, eventDurationInMinutes, eventCapacity);
		assertEquals(HRConstants.EVENT_ALREADY_ADDED, result);
	}
}
