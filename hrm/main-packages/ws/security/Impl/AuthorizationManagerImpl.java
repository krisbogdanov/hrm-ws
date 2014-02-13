/**
 * 
 */
package ws.security.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ws.dao.Token;

import ws.security.AuthorizationManager;
import ws.services.IManageToken;
import ws.services.Impl.ManageToken;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class AuthorizationManagerImpl implements AuthorizationManager {
	
	private static final String SELECT_TOKEN = "SELECT * FROM hr.Token WHERE token = ?;";
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.security.AuthorizationManager#isAuthorizedTo(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isAuthorizedTo(String token, String permission) {
		if(token != null && permission != null) {
			int permitTo = Integer.parseInt(permission);
			Token t = getTokenObject(token);
			if(t != null) {
				//is the token expired
				Date today = new Date();
				if(today.before(t.getTokenExpire())) { //if not expired
					if(t.getPermissions() >= permitTo) {
						return true;
					} else {
						return false;
					}
				} else {
					IManageToken tokenManager = new ManageToken();
					tokenManager.deleteToken(t.getToken());
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see ws.security.AuthorizationManager#getEmployeeIdFromToken(java.lang.String)
	 */
	@Override
	public int getEmployeeIdFromToken(String token) {
		Token t = getTokenObject(token);
		if(t != null) {
			return t.getEmployeeId();
		} else {
			return 0;
		}
	}
	@Override
	public boolean isTheOwnerOf(String token, int targetId) {
		Token t = getTokenObject(token);
		if(t != null) {
			if(t.getEmployeeId() == targetId) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	private Token getTokenObject(String token) {
		try {
			PreparedStatement select = connection.prepareStatement(SELECT_TOKEN);
			select.setString(1, token);
			ResultSet result = select.executeQuery();
			if(result.next()) {
				return new Token(result.getString(HRConstants.TOKEN),
								 result.getInt(HRConstants.EMPLOYEE_ID),
								 result.getDate(HRConstants.TOKEN_EXPIRE),
								 result.getInt(HRConstants.PERMISSIONS));
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
