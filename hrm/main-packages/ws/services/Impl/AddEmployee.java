/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ws.services.IAddEmployee;
import ws.utils.Impl.DatabaseConnection;

/**
 * @author Kristiyan
 *
 */
@WebService(targetNamespace = "http://Impl.services.ws/", portName = "AddEmployeePort", serviceName = "AddEmployeeService")
public class AddEmployee implements IAddEmployee {
	
//	private DatabaseConnection db = new DatabaseConnectionImpl("hr", "root", "m325bp");
	private Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IAddEmployee#addEmployee(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@WebMethod(operationName = "addEmployee", action = "urn:AddEmployee")
	@RequestWrapper(className = "ws.services.Impl.jaxws.AddEmployee", localName = "addEmployee", targetNamespace = "http://Impl.services.ws/")
	@ResponseWrapper(className = "ws.services.Impl.jaxws.AddEmployeeResponse", localName = "addEmployeeResponse", targetNamespace = "http://Impl.services.ws/")
	@Override
	public String addEmployee(final String employeeName, final String employeeSurname,
			final String employeeEmail, final String employeeAddress,
			final String employeeSSN, final String employeePhone) {
		try {
			Date employeeJoined = new Date();
			PreparedStatement insertStatement = connection
					.prepareStatement("INSERT INTO hr.employee values (default, ?, ?, ?, ?, ?, ?, ?);");
			insertStatement.setString(1, employeeName);
			insertStatement.setString(2, employeeSurname);
			insertStatement.setString(3, employeeEmail);
			insertStatement.setString(4, employeeAddress);
			insertStatement.setString(5, employeeSSN);
			insertStatement.setString(6, employeePhone);
			insertStatement.setObject(7, employeeJoined);
			insertStatement.executeUpdate();
			return "Success";
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
	}

}
