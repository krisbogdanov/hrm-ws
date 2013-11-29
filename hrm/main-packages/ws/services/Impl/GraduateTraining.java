/**
 * 
 */
package ws.services.Impl;

import java.util.Date;
import java.util.List;

import ws.services.IGraduateTraining;

/**
 * @author Kristiyan
 *
 */
public class GraduateTraining implements IGraduateTraining {

	/* (non-Javadoc)
	 * @see ws.services.IGraduateTraining#addGraduateTraining(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public int addGraduateTraining(String token, String location, Date starts,
			Date ends) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ws.services.IGraduateTraining#removeGraduateTrainigByLocation(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeGraduateTrainigByLocation(String token, String location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ws.dao.GraduateTraining getGraduateTrainingIdByLocation(
			String token, String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ws.dao.GraduateTraining> getAllGraduateTraining(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
