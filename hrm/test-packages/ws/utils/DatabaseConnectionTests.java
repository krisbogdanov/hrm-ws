package ws.utils;

import java.sql.Connection;

import ws.utils.Impl.DatabaseConnection;

import junit.framework.TestCase;

public class DatabaseConnectionTests extends TestCase {
	private Connection connection = DatabaseConnection.getConnection();
	public void testGetDatabaseConnectionIsNotNull() {
		assertNotNull(connection);
	}
}
