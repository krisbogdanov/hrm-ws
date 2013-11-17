/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ws.services.IAddStudent;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class AddStudent implements IAddStudent {
	
	
	private final Connection connection = DatabaseConnection.getConnection();
	
	/* (non-Javadoc)
	 * @see ws.services.IAddStudent#addStudent(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addStudent(String studentName, String studentSurname,
			String studentEmail) {
		try {
			PreparedStatement checkStatement = connection
					.prepareStatement(HRConstants.SELECT_STUDENT_BY_EMAIL_STATEMENT);
			checkStatement.setString(1, studentEmail);
			ResultSet resultSet = checkStatement.executeQuery();
			if(resultSet.first()) {
				return HRConstants.STUDENT_ALREADY_ADDED;
			} else {
				Date studentRegistered = new Date();
				PreparedStatement statement = connection
						.prepareStatement(HRConstants.INSERT_STUDENT_STATEMENT);
				statement.setString(1, studentName);
				statement.setString(2, studentSurname);
				statement.setString(3, studentEmail);
				statement.setObject(4, studentRegistered);
				statement.executeUpdate();
				return HRConstants.INSERT_SUCCESS;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
