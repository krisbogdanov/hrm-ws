package ws.services;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import ws.dao.Employee;
import ws.dao.GraduateTraining;
import ws.services.Impl.ManageGraduateTraining;

import junit.framework.TestCase;

public class ManageGraduateTrainingTests extends TestCase {
	private IManageGraduateTraining manager = new ManageGraduateTraining();
	private String writeToken = "writeToken";
	private String readToken = "readToken";
	private Date start = new Date();
	private Date end = new Date(System.currentTimeMillis() + 86400 * 1000 * 2);
	
	@Test
	public void testGetAllGradTrain() {
		List<GraduateTraining> listWithWritePerm = manager.getAllGraduateTraining(writeToken, false);
		assertNotNull(listWithWritePerm);
		System.out.println("WRITE PERM: " + listWithWritePerm.toString());
		List<GraduateTraining> listWithReadPerm = manager.getAllGraduateTraining(readToken, false);
		assertNotNull(listWithReadPerm);
		System.out.println("READ PERM: " + listWithReadPerm.toString());
	}
	@Test
	public void testGetGradTrainByLocation() {
		GraduateTraining training = manager.getGraduateTrainingByLocation(readToken, "London", false);
		assertNotNull(training);
		GraduateTraining trainingWithDiffToken = manager.getGraduateTrainingByLocation(writeToken, "New York", false);
		assertNotNull(trainingWithDiffToken);
	}
	@Test
	public void testAddGradTrainAndRemoveIt() {
		int add = manager.addGraduateTraining(writeToken, "Sofia", start, end, false);
		assertEquals(1, add);
		int failToAdd = manager.addGraduateTraining(readToken, "Pleven", start, end, false);
		assertEquals(0, failToAdd);
		GraduateTraining training = manager.getGraduateTrainingByLocation(writeToken, "Sofia", false);
		assertNotNull(training);
		int failToRemove = manager.removeGraduateTrainigByLocation(readToken, training.getGradTrainingLocation(), false);
		assertEquals(0, failToRemove);
		int remove = manager.removeGraduateTrainigByLocation(writeToken, training.getGradTrainingLocation(), false);
		assertEquals(1, remove);
	}
	@Test
	public void testGetEmployeesRegisteredForGradTraining() {
		List<Employee> list = manager.getEmployeesRegisteredForGradTraining(readToken, 6, false);
		assertNotNull(list);
		System.out.println("Employees: " + list.toString());
	}
}
