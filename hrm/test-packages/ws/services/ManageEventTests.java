package ws.services;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import ws.dao.Event;
import ws.services.Impl.ManageEvent;
import junit.framework.TestCase;

public class ManageEventTests extends TestCase {
	private IManageEvent manager = new ManageEvent();
	private String writeToken = "writeToken";
	private String readToken = "readToken";
	private Date date = new Date();
	@Test
	public void testGetAllEvents() {
		List<Event> listWithWrite = manager.getAllEvents(writeToken);
		assertNotNull(listWithWrite);
		System.out.println("WRITE PERM: " + listWithWrite.toString());
		List<Event> listWithRead = manager.getAllEvents(readToken);
		assertNotNull(listWithRead);
		System.out.println("READ PERM: " + listWithRead.toString());
	}
	
	@Test
	public void testAddEventGetItByNameAndRemoveIt() {
		int add = manager.addEvent(writeToken, "JUnit Test", "LND", date, 200, 50);
		assertEquals(1, add);
		int failToAdd = manager.addEvent(readToken, "Fail", "LND", date, 200, 50);
		assertEquals(0, failToAdd);
		Event event = manager.getEventByName(readToken, "JUnit Test");
		assertNotNull(event);
		int failToRemove = manager.removeEventByName(readToken, event.getEventName());
		assertEquals(0, failToRemove);
		int remove = manager.removeEventByName(writeToken, event.getEventName());
		assertEquals(1, remove);
	}
	@Test
	public void testEditEvent() {
		int failToEdit = manager.editEventById(readToken, 3, "Presentation", "London", date, 200, 300);
		assertEquals(0, failToEdit);
		int edit = manager.editEventById(writeToken, 3, "Presentation", "London", date, 200, 300);
		assertEquals(1, edit);
	}
}
