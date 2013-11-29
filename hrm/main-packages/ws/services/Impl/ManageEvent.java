/**
 * 
 */
package ws.services.Impl;

import java.util.Date;
import java.util.List;

import ws.dao.Event;
import ws.services.IManageEvent;

/**
 * @author Kristiyan
 *
 */
public class ManageEvent implements IManageEvent {

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#addEvent(java.lang.String, java.lang.String, java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public int addEvent(String token, String eventName, String eventLocation,
			Date eventDate, int eventDurationInMinutes, int eventCapacity) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#removeEventByName(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeEventByName(String token, String eventName) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#editEventById(java.lang.String, java.lang.String, java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public int editEventById(String token, String eventName,
			String eventLocation, Date eventDate, int eventDurationInMinutes,
			int eventCapacity) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#getEventByName(java.lang.String, java.lang.String)
	 */
	@Override
	public Event getEventByName(String token, String eventName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#getAllEvents(java.lang.String)
	 */
	@Override
	public List<Event> getAllEvents(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
