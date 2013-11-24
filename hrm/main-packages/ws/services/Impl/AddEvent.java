/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ws.services.IAddEvent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
@WebService(targetNamespace = "http://Impl.services.ws/", portName = "AddEventPort", serviceName = "AddEventService")
public class AddEvent implements IAddEvent {
	

	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IAddEvent#addEvent(java.lang.String, java.lang.String, java.sql.Date, int, int)
	 */
	@WebMethod(operationName = "addEvent", action = "urn:AddEvent")
	@RequestWrapper(className = "ws.services.Impl.jaxws.AddEvent", localName = "addEvent", targetNamespace = "http://Impl.services.ws/")
	@ResponseWrapper(className = "ws.services.Impl.jaxws.AddEventResponse", localName = "addEventResponse", targetNamespace = "http://Impl.services.ws/")
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
