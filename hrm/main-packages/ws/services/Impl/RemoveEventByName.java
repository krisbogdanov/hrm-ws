/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ws.services.IRemoveEventByName;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class RemoveEventByName implements IRemoveEventByName {
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IRemoveEventByName#removeEventByName(java.lang.String)
	 */
	@Override
	public String removeEventByName(String eventName) {
		try {
			PreparedStatement deleteStatement = connection
					.prepareStatement(HRConstants.DELETE_EVENT_BY_NAME);
			deleteStatement.setString(1, eventName);
			int result = deleteStatement.executeUpdate(); //0 for nothing or the row count
			if(result == 0) {
				return HRConstants.EVENT_DOES_NOT_EXIST;
			} else {
				return HRConstants.DELETE_SUCCESS;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
