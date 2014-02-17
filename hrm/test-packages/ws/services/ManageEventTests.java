package ws.services;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import ws.dao.Event;
import ws.dao.Student;
import ws.services.Impl.ManageEvent;
import junit.framework.TestCase;

public class ManageEventTests extends TestCase {
	private IManageEvent manager = new ManageEvent();
	private String writeToken = "writeToken";
	private String readToken = "readToken";
	private Date date = new Date();
	@Test
	public void testGetAllEvents() {
		List<Event> listWithWrite = manager.getAllEvents(writeToken, false);
		assertNotNull(listWithWrite);
		System.out.println("WRITE PERM: " + listWithWrite.toString());
		List<Event> listWithRead = manager.getAllEvents(readToken, false);
		assertNotNull(listWithRead);
		System.out.println("READ PERM: " + listWithRead.toString());
	}
	
	@Test
	public void testAddEventGetItByNameAndRemoveIt() {
		int add = manager.addEvent(writeToken, "JUnit Test", "LND", date, 200, 50, false);
		assertEquals(1, add);
		int failToAdd = manager.addEvent(readToken, "Fail", "LND", date, 200, 50, false);
		assertEquals(0, failToAdd);
		Event event = manager.getEventByName(readToken, "JUnit Test", false);
		assertNotNull(event);
		int failToRemove = manager.removeEventByName(readToken, event.getEventName(), false);
		assertEquals(0, failToRemove);
		int remove = manager.removeEventByName(writeToken, event.getEventName(), false);
		assertEquals(1, remove);
	}
	@Test
	public void testEditEvent() {
		int failToEdit = manager.editEventById(readToken, 3, "Presentation", "London", date, 200, 300, false);
		assertEquals(0, failToEdit);
		int edit = manager.editEventById(writeToken, 3, "Presentation", "London", date, 200, 300, false);
		assertEquals(1, edit);
	}
	@Test
	public void testGetRegisteredStudentsForEvent() {
		List<Student> list = manager.getStudentsRegisteredForEvent(readToken, 3, false);
		assertNotNull(list);
		System.out.println("Students: " + list.toString());
	}
}
