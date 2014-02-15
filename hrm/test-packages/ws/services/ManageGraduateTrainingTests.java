package ws.services;

import java.util.Date;
import java.util.List;

import org.junit.Test;

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
		List<GraduateTraining> listWithWritePerm = manager.getAllGraduateTraining(writeToken);
		assertNotNull(listWithWritePerm);
		System.out.println("WRITE PERM: " + listWithWritePerm.toString());
		List<GraduateTraining> listWithReadPerm = manager.getAllGraduateTraining(readToken);
		assertNotNull(listWithReadPerm);
		System.out.println("READ PERM: " + listWithReadPerm.toString());
	}
	@Test
	public void testGetGradTrainByLocation() {
		GraduateTraining training = manager.getGraduateTrainingByLocation(readToken, "London");
		assertNotNull(training);
		GraduateTraining trainingWithDiffToken = manager.getGraduateTrainingByLocation(writeToken, "New York");
		assertNotNull(trainingWithDiffToken);
	}
	@Test
	public void testAddGradTrainAndRemoveIt() {
		int add = manager.addGraduateTraining(writeToken, "Sofia", start, end);
		assertEquals(1, add);
		int failToAdd = manager.addGraduateTraining(readToken, "Pleven", start, end);
		assertEquals(0, failToAdd);
		GraduateTraining training = manager.getGraduateTrainingByLocation(writeToken, "Sofia");
		assertNotNull(training);
		int failToRemove = manager.removeGraduateTrainigByLocation(readToken, training.getGradTrainingLocation());
		assertEquals(0, failToRemove);
		int remove = manager.removeGraduateTrainigByLocation(writeToken, training.getGradTrainingLocation());
		assertEquals(1, remove);
	}
}
