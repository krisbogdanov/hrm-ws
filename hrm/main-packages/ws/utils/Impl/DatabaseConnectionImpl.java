/**
 * 
 */
package ws.utils.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.*;

import ws.utils.DatabaseConnection;

/**
 * @author Kristiyan
 *
 */
@Repository("DatabaseConnection")
public class DatabaseConnectionImpl implements DatabaseConnection {
	
	private Connection connection;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	public DatabaseConnectionImpl(){}
	public DatabaseConnectionImpl(final String dbName, final String dbUser, final String dbPassword) {
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		initializeConnection();
	}
	public void initializeConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager
		          .getConnection(String.format("jdbc:mysql://localhost/%s?user=%s&password=%s", 
		        		  						this.dbName, this.dbUser, this.dbPassword));
		} catch (ClassNotFoundException classEx) {
			classEx.printStackTrace();
		} catch (SQLException sql){
			sql.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see ws.utils.DatabaseConnection#getConnection()
	 */
	@Override
	public Connection getConnection() {
		if(this.connection != null) {
			return this.connection;
		} else {
			return null;
		}
	}

}
