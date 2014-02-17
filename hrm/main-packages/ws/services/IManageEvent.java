/**
 * 
 */
package ws.services;

import java.util.Date;
import java.util.List;

import ws.dao.Event;
import ws.dao.Student;

/**
 * @author Kristiyan
 *
 */
public interface IManageEvent {
	public int addEvent(final String token, final String eventName,
			final String eventLocation, final Date eventDate,
			final int eventDurationInMinutes, final int eventCapacity, boolean secure);
	public int removeEventByName(final String token, final String eventName, boolean secure);
	public int editEventById(final String token,int eventId, final String eventName,
			final String eventLocation, final Date eventDate,
			final int eventDurationInMinutes, final int eventCapacity, boolean secure);
	public Event getEventByName(final String token, final String eventName, boolean secure);
	public List<Event> getAllEvents(final String token, boolean secure);
	public List<Student> getStudentsRegisteredForEvent(final String token, final int eventId, boolean secure);
	
}
