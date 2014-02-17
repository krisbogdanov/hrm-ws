/**
 * 
 */
package ws.services;

import java.util.Date;
import java.util.List;

import ws.dao.Employee;
import ws.dao.GraduateTraining;

/**
 * @author Kristiyan
 *
 */
public interface IManageGraduateTraining {
	public int addGraduateTraining(final String token, final String location,
			final Date starts, final Date ends, boolean secure);
	public int removeGraduateTrainigByLocation(final String token, final String location, boolean secure);
	public GraduateTraining getGraduateTrainingByLocation(final String token, final String location, boolean secure);
	public List<GraduateTraining> getAllGraduateTraining(final String token, boolean secure);
	public List<Employee> getEmployeesRegisteredForGradTraining(final String token, final int gradTrainingId, boolean secure);
}
