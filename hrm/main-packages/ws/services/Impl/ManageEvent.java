/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ws.dao.Event;
import ws.services.IManageEvent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageEvent implements IManageEvent {
	
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#addEvent(java.lang.String, java.lang.String, java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public int addEvent(String token, String eventName, String eventLocation,
			Date eventDate, int eventDurationInMinutes, int eventCapacity) {
		try {
			Event event = getEventByName(token, eventName);
			if(event == null) {
				PreparedStatement insert = connection.
						prepareStatement(HRConstants.INSERT_EVENT);
				insert.setString(0, eventName);
				insert.setString(1, eventLocation);
				insert.setObject(2, eventDate);
				insert.setInt(3, eventDurationInMinutes);
				insert.setInt(4, eventCapacity);
				int result = insert.executeUpdate();
				if(result == 0) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#removeEventByName(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeEventByName(String token, String eventName) {
		try {
			PreparedStatement delete = connection.
					prepareStatement(HRConstants.DELETE_EVENT_BY_NAME);
			delete.setString(0, eventName);
			int result = delete.executeUpdate();
			delete.close();
			if(result == 0) {
				return 0;
			} else {
				return 1;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#editEventById(java.lang.String, java.lang.String, java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public int editEventById(String token, int eventId, String eventName,
			String eventLocation, Date eventDate, int eventDurationInMinutes,
			int eventCapacity) {
		try {
			Event event = getEventByName(token, eventName);
			if(event == null) {
				return 0;
			} else {
				PreparedStatement update = connection.
						prepareStatement(HRConstants.UPDATE_EVENT);
				update.setString(0, eventName);
				update.setString(1, eventLocation);
				update.setObject(2, eventDate);
				update.setInt(3, eventDurationInMinutes);
				update.setInt(4, eventCapacity);
				update.setInt(5, eventId);
				int result = update.executeUpdate();
				if(result == 0) {
					return 0;
				} else {
					return 1;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#getEventByName(java.lang.String, java.lang.String)
	 */
	@Override
	public Event getEventByName(String token, String eventName) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_EVENT_BY_NAME);
			select.setString(0, eventName);
			ResultSet result = select.executeQuery();
			select.close();
			if(result.next()) {
				Event event = new Event(
						result.getInt(HRConstants.EVENT_ID),
						result.getString(HRConstants.EVENT_NAME),
						result.getString(HRConstants.EVENT_LOCATION),
						result.getDate(HRConstants.EVENT_DATE),
						result.getInt(HRConstants.EVENT_DURATION_IN_MINUTES),
						result.getInt(HRConstants.EVENT_CAPACITY));
				return event;
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IManageEvent#getAllEvents(java.lang.String)
	 */
	@Override
	public List<Event> getAllEvents(String token) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_ALL_EVENTS);
			ResultSet result = select.executeQuery();
			List<Event> list = generateEventList(result);
			select.close();
			if(list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<Event> generateEventList(ResultSet result) throws SQLException {
		List<Event> list = new ArrayList<Event>();
		while(result.next()) {
			Event event = new Event(
					result.getInt(HRConstants.EVENT_ID),
					result.getString(HRConstants.EVENT_NAME),
					result.getString(HRConstants.EVENT_LOCATION),
					result.getDate(HRConstants.EVENT_DATE),
					result.getInt(HRConstants.EVENT_DURATION_IN_MINUTES),
					result.getInt(HRConstants.EVENT_CAPACITY));
			list.add(event);
		}
		return list;
	}

}
