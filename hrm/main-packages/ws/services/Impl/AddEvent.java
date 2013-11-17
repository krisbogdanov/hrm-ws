/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ws.services.IAddEvent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class AddEvent implements IAddEvent {
	

	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IAddEvent#addEvent(java.lang.String, java.lang.String, java.sql.Date, int, int)
	 */
	@Override
	public String addEvent(String eventName, String eventLocation,
			Date eventDate, int eventDurationInMinutes, int eventCapacity) {
		try {
			PreparedStatement checkStatement = connection
					.prepareStatement(HRConstants.SELECT_EVENT_BY_NAME_STATEMENT);
			checkStatement.setString(1, eventName);
			ResultSet resultSet = checkStatement.executeQuery();
			if(resultSet.first()) {
				return HRConstants.EVENT_ALREADY_ADDED;
			} else {
				PreparedStatement insertStatement = connection
						.prepareStatement(HRConstants.INSERT_EVENT_STATEMENT);
				insertStatement.setString(1, eventName);
				insertStatement.setString(2, eventLocation);
				insertStatement.setObject(3, eventDate);
				insertStatement.setInt(4, eventDurationInMinutes);
				insertStatement.setInt(5, eventCapacity);
				insertStatement.executeUpdate();
				return HRConstants.INSERT_SUCCESS;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
