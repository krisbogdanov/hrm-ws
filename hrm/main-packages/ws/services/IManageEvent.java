/**
 * 
 */
package ws.services;

import java.util.Date;
import java.util.List;

import ws.dao.Event;

/**
 * @author Kristiyan
 *
 */
public interface IManageEvent {
	public int addEvent(final String token, final String eventName,
			final String eventLocation, final Date eventDate,
			final int eventDurationInMinutes, final int eventCapacity);
	public int removeEventByName(final String token, final String eventName);
	public int editEventById(final String token, final String eventName,
			final String eventLocation, final Date eventDate,
			final int eventDurationInMinutes, final int eventCapacity);
	public Event getEventByName(final String token, final String eventName);
	public List<Event> getAllEvents(final String token);
	
}
