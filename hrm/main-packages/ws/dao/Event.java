/**
 * 
 */
package ws.dao;
import java.util.Date;
/**
 * @author Kristiyan
 *
 */
public class Event {
	private int eventId;
	private String eventName;
	private String eventLocation;
	private Date eventDate;
	private int eventDurationInMinutes;
	private int eventCapacity;
	
	public Event() {}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventLocation
	 * @param eventDate
	 * @param eventDurationInMinutes
	 * @param eventCapacity
	 */
	public Event(int eventId, String eventName, String eventLocation,
			Date eventDate, int eventDurationInMinutes, int eventCapacity) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventLocation = eventLocation;
		this.eventDate = eventDate;
		this.eventDurationInMinutes = eventDurationInMinutes;
		this.eventCapacity = eventCapacity;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getEventDurationInMinutes() {
		return eventDurationInMinutes;
	}

	public void setEventDurationInMinutes(int eventDurationInMinutes) {
		this.eventDurationInMinutes = eventDurationInMinutes;
	}

	public int getEventCapacity() {
		return eventCapacity;
	}

	public void setEventCapacity(int eventCapacity) {
		this.eventCapacity = eventCapacity;
	}
	
}
