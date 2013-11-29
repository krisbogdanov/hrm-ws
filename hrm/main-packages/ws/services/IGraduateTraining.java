/**
 * 
 */
package ws.services;

import java.util.Date;
import java.util.List;

import ws.dao.GraduateTraining;

/**
 * @author Kristiyan
 *
 */
public interface IGraduateTraining {
	public int addGraduateTraining(final String token, final String location,
			final Date starts, final Date ends);
	public int removeGraduateTrainigByLocation(final String token, final String location);
	public GraduateTraining getGraduateTrainingIdByLocation(final String token, final String location);
	public List<GraduateTraining> getAllGraduateTraining(final String token);
}
