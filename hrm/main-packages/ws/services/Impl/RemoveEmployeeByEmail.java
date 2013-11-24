package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ws.services.IRemoveEmployeeByEmail;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

public class RemoveEmployeeByEmail implements IRemoveEmployeeByEmail {
	private final Connection connection = DatabaseConnection.getConnection();
	@Override
	public String removeEmployeeByEmail(String email) {
		try {
			PreparedStatement deleteStatement = connection
					.prepareStatement(HRConstants.DELETE_EMPLOYEE_BY_EMAIL);
			deleteStatement.setString(1, email);
			int result = deleteStatement.executeUpdate(); //0 for nothing or the row count
			if(result == 0) {
				return HRConstants.EMPLOYEE_DOES_NOT_EXIST;
			} else {
				return HRConstants.DELETE_SUCCESS;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
