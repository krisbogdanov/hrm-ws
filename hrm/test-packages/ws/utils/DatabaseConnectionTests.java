package ws.utils;

import java.sql.Connection;

import ws.utils.Impl.DatabaseConnectionImpl;

import junit.framework.TestCase;

public class DatabaseConnectionTests extends TestCase {
	private DatabaseConnection db = new DatabaseConnectionImpl("hr", "root", "m325bp");
	private Connection connection = db.getConnection();
	
	public void testGetDatabaseConnectionIsNotNull() {
		assertNotNull(connection);
	}
}
