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
import ws.dao.Student;
import ws.security.AuthorizationManager;
import ws.security.InputValidationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.security.Impl.InputValidationManagerImpl;
import ws.services.IManageEvent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageEvent implements IManageEvent {
	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();
	private final InputValidationManager validationManager = new InputValidationManagerImpl();
	@Override
	public int addEvent(String token, String eventName, String eventLocation,
			Date eventDate, int eventDurationInMinutes, int eventCapacity, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(eventName) ||
						!validationManager.textValidation(eventLocation) ||
						!validationManager.dateValidation(eventDate) ||
						!validationManager.integerValidation(eventCapacity) ||
						!validationManager.integerValidation(eventDurationInMinutes)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				Event event = getEventByName(token, eventName, secure);
				if(event == null) {
					PreparedStatement insert = connection.
							prepareStatement(HRConstants.INSERT_EVENT);
					insert.setString(1, eventName);
					insert.setString(2, eventLocation);
					insert.setObject(3, eventDate);
					insert.setInt(4, eventDurationInMinutes);
					insert.setInt(5, eventCapacity);
					int result = insert.executeUpdate();
					if(result == 0) {
						return 0;
					} else {
						return 1;
					}
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeEventByName(String token, String eventName, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(eventName)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				Event event = getEventByName(token, eventName, secure);
				PreparedStatement delete = connection.
						prepareStatement(HRConstants.DELETE_EVENT_BY_NAME);
				delete.setString(1, eventName);
				int result = delete.executeUpdate();
				PreparedStatement deleteMappings = connection.
						prepareStatement(HRConstants.DELETE_EVENT_MAPPINGS);
				deleteMappings.setInt(1, event.getEventId());
				deleteMappings.executeUpdate();
				deleteMappings.close();
				delete.close();
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

	@Override
	public int editEventById(String token, int eventId, String eventName,
			String eventLocation, Date eventDate, int eventDurationInMinutes,
			int eventCapacity, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(eventName) ||
						!validationManager.integerValidation(eventId) ||
						!validationManager.textValidation(eventLocation) ||
						!validationManager.dateValidation(eventDate) ||
						!validationManager.integerValidation(eventCapacity) ||
						!validationManager.integerValidation(eventDurationInMinutes)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				Event event = getEventByName(token, eventName, secure);
				if(event == null) {
					return 0;
				} else {
					PreparedStatement update = connection.
							prepareStatement(HRConstants.UPDATE_EVENT);
					update.setString(1, eventName);
					update.setString(2, eventLocation);
					update.setObject(3, eventDate);
					update.setInt(4, eventDurationInMinutes);
					update.setInt(5, eventCapacity);
					update.setInt(6, eventId);
					int result = update.executeUpdate();
					if(result == 0) {
						return 0;
					} else {
						return 1;
					}
				}
			} else {
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Event getEventByName(String token, String eventName, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(eventName)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_EVENT_BY_NAME);
				select.setString(1, eventName);
				ResultSet result = select.executeQuery();
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
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Event> getAllEvents(String token, boolean secure) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_ALL_EVENTS);
				ResultSet result = select.executeQuery();
				List<Event> list = generateEventList(result);
				if(list.isEmpty()) {
					return null;
				} else {
					return list;
				}
			} else {
				return null;
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

	@Override
	public List<Student> getStudentsRegisteredForEvent(String token,
			int eventId, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(eventId)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.prepareStatement(HRConstants.SELECT_STUDENTS_FOR_EVENT);
				select.setInt(1, eventId);
				ResultSet result = select.executeQuery();
				List<Student> list = new ArrayList<Student>();
				while(result.next()) {
					int studentId = result.getInt(HRConstants.STUDENT_ID);
					PreparedStatement selectStudent = connection.prepareStatement(HRConstants.SELECT_STUDENT_BY_ID);
					selectStudent.setInt(1, studentId);
					ResultSet studentResult = selectStudent.executeQuery();
					if(studentResult.next()) {
						Student student = new Student(studentResult.getInt(HRConstants.STUDENT_ID),
								studentResult.getString(HRConstants.STUDENT_NAME),
								studentResult.getString(HRConstants.STUDENT_SURNAME),
								studentResult.getString(HRConstants.STUDENT_EMAIL),
								studentResult.getDate(HRConstants.STUDENT_REGISTERED));
						list.add(student);
					}
				}
				return list;
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
