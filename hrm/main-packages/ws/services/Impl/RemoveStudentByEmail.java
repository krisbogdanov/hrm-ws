/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ws.services.IRemoveStudentByEmail;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class RemoveStudentByEmail implements IRemoveStudentByEmail {
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IRemoveStudentByEmail#removeStudentByEmail(java.lang.String)
	 */
	@Override
	public String removeStudentByEmail(String email) {
		try {
			PreparedStatement deleteStatement = connection
					.prepareStatement(HRConstants.DELETE_STUDENT_BY_EMAIL);
			deleteStatement.setString(1, email);
			int result = deleteStatement.executeUpdate(); //0 for nothing or the row count
			if(result == 0) {
				return HRConstants.STUDENT_DOES_NOT_EXIST;
			} else {
				return HRConstants.DELETE_SUCCESS;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
