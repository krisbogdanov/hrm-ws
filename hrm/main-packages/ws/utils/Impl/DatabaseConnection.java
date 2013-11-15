/**
 * 
 */
package ws.utils.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Kristiyan
 * Implemented with a singleton pattern
 */
public class DatabaseConnection {
	
	private static Connection connection = null;
	private static String dbName = "hr";
	private static String dbUser = "root";
	private static String dbPassword = "m325bp";
	private DatabaseConnection(){}
	private static void initializeConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
		          .getConnection(String.format("jdbc:mysql://localhost/%s?user=%s&password=%s", 
		        		  						dbName, dbUser, dbPassword));
		} catch (ClassNotFoundException classEx) {
			classEx.printStackTrace();
		} catch (SQLException sql){
			sql.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see ws.utils.DatabaseConnection#getConnection()
	 */
	public static synchronized Connection getConnection() {
		if(connection == null) {
			initializeConnection();
		}
		return connection;
	}

}
