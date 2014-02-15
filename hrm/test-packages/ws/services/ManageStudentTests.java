package ws.services;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import ws.dao.Student;
import ws.services.Impl.ManageStudent;
import junit.framework.TestCase;

public class ManageStudentTests extends TestCase {
	private IManageStudent manager = new ManageStudent();
	private String writeToken = "writeToken";
	private String readToken = "readToken";
	
	@Test
	public void testGetAllStudents() {
		List<Student> listWithWrite = manager.getAllStudents(writeToken);
		assertNotNull(listWithWrite);
		System.out.println("WRITE PERM: " + listWithWrite.toString());
		List<Student> listWithRead = manager.getAllStudents(readToken);
		assertNotNull(listWithRead);
		System.out.println("READ PERM: " + listWithRead.toString());
	}
	@Test
	public void testGetStudentByEmail() {
		Student studentWithWrite = manager.getStudentByEmail(writeToken, "js@gmail.com");
		assertNotNull(studentWithWrite);
		System.out.println("WRITE PERM: " + studentWithWrite.toString());
		Student studentWithRead = manager.getStudentByEmail(readToken, "js1@gmail.com");
		assertNotNull(studentWithRead);
		System.out.println("READ PERM: " + studentWithRead.toString());
	}
	@Test
	public void testSearchStudentByName() {
		List<Student> search = manager.searchStudentByName(readToken, "%J%");
		assertNotNull(search);
		List<Student> searchEmpty = manager.searchStudentByName(writeToken, "J");
		assertNull(searchEmpty);
	}
	
	@Test
	public void testAddStudentGetItByEmailAndRemoveIt() {
		Date date = new Date();
		int add = manager.addStudent(writeToken, "Test", "Subject", "subject@test.com", date);
		assertEquals(1, add);
		int failToAdd = manager.addStudent(readToken, "Fail", "Subject", "subject@test.com", date);
		assertEquals(0, failToAdd);
		Student student = manager.getStudentByEmail(readToken, "subject@test.com");
		assertNotNull(student);
		int failToRemove = manager.removeStudentByEmail(readToken, student.getStudentEmail());
		assertEquals(0, failToRemove);
		int remove = manager.removeStudentByEmail(writeToken, student.getStudentEmail());
		assertEquals(1, remove);
	}
	
	@Test
	public void testAddStudentThenRegisterForEventThenUnregisterThenRemoveStudent() {
		Date date = new Date();
		int add = manager.addStudent(writeToken, "Test", "Subject", "subject@test.com", date);
		assertEquals(1, add);
		Student student = manager.getStudentByEmail(readToken, "subject@test.com");
		assertNotNull(student);
		int register = manager.registerStudentToEvent(writeToken, student.getStudentId(), 3);
		assertEquals(1, register);
		int failToRegister = manager.registerStudentToEvent(readToken, student.getStudentId(), 4);
		assertEquals(0, failToRegister);
		int failToUnregister = manager.unregisterStudentFromEvent(readToken, student.getStudentId(), 3);
		assertEquals(0, failToUnregister);
		int unregister = manager.unregisterStudentFromEvent(writeToken, student.getStudentId(), 3);
		assertEquals(1, unregister); 
		int remove = manager.removeStudentByEmail(writeToken, student.getStudentEmail());
		assertEquals(1, remove);
	}
}
