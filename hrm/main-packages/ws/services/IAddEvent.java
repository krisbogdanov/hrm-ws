/**
 * 
 */
package ws.services;

import java.util.Date;

/**
 * @author Kristiyan
 *
 */
public interface IAddEvent {
	public String addEvent(final String eventName, final String eventLocation,
			final Date eventDate, final int eventDurationInMinutes,
			final int eventCapacity);
}
