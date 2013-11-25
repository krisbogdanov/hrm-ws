/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ws.services.IRegisterStudentForEvent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class RegisterStudentForEvent implements IRegisterStudentForEvent {
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IRegisterStudentForEvent#registerStudentForEvent(int, int)
	 */
	@Override
	public String registerStudentForEvent(int studentId, int eventId) {
		try {
			PreparedStatement checkStatement = connection
					.prepareStatement(HRConstants.SELECT_REGISTRATION_BY_IDS);
			checkStatement.setInt(1, studentId);
			checkStatement.setInt(2, eventId);
			ResultSet resultSet = checkStatement.executeQuery();
			if(resultSet.first()) {
				return HRConstants.STUDENT_ALREADY_REGISTERED;
			} else {
				PreparedStatement insertStatement = connection
						.prepareStatement(HRConstants.INSERT_STUDENT_TO_EVENT);
				insertStatement.setInt(1, studentId);
				insertStatement.setInt(2, eventId);
				insertStatement.executeUpdate();
				return HRConstants.INSERT_SUCCESS;
			}
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
	}

}
