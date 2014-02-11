/**
 * 
 */
package ws.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ws.dao.GraduateTraining;
import ws.services.IManageGraduateTraining;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageGraduateTraining implements IManageGraduateTraining {
	
	private final Connection connection = DatabaseConnection.getConnection();
	/* (non-Javadoc)
	 * @see ws.services.IGraduateTraining#addGraduateTraining(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public int addGraduateTraining(String token, String location, Date starts,
			Date ends) {
		try {
			GraduateTraining gradTraining = getGraduateTrainingByLocation(token, location);
			if(gradTraining != null) {
				return 0;
			} else {
				PreparedStatement insertStatement = connection.
						prepareStatement(HRConstants.INSERT_GRAD_TRAINING_STATEMENT);
				insertStatement.setString(0, location);
				insertStatement.setObject(1, starts);
				insertStatement.setObject(2, ends);
				int result = insertStatement.executeUpdate();
				insertStatement.close();
				if(result == 0) {
					return 0;
				} else {
					return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see ws.services.IGraduateTraining#removeGraduateTrainigByLocation(java.lang.String, java.lang.String)
	 */
	@Override
	public int removeGraduateTrainigByLocation(String token, String location) {
		try {
			GraduateTraining gradTraining = getGraduateTrainingByLocation(token, location);
			
			PreparedStatement deleteStatement = connection.
					prepareStatement(HRConstants.REMOVE_GRAD_TRAINING_BY_LOCATION);
			deleteStatement.setString(0, location);
			int result = deleteStatement.executeUpdate();
			PreparedStatement deleteMappings = connection.
					prepareStatement(HRConstants.REMOVE_GRAD_TRAINING_MAPPINGS);
			deleteMappings.setInt(0, gradTraining.getGradTrainingId());
			deleteMappings.executeUpdate();
			deleteMappings.close();
			deleteStatement.close();
			if(result == 0) {
				return 0;
			} else {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}


	@Override
	public List<GraduateTraining> getAllGraduateTraining(String token) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_ALL_GRAD_TRAININGS);
			ResultSet result = select.executeQuery();
			List<GraduateTraining> gradTrainingList = generateGradTrainingList(result);
			select.close();
			if(gradTrainingList.isEmpty()) {
				return null;
			} else {
				return gradTrainingList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<GraduateTraining> generateGradTrainingList(ResultSet result)
			throws SQLException {
		List<GraduateTraining> gradTrainingList = new ArrayList<GraduateTraining>();
		while(result.next()) {
			GraduateTraining gradTrainining = new GraduateTraining(
					result.getInt(HRConstants.GRAD_TRAINING_ID), 
					result.getString(HRConstants.GRAD_TRAINING_LOCATION),
					result.getDate(HRConstants.GRAD_TRAINING_STARTS),
					result.getDate(HRConstants.GRAD_TRAINING_ENDS));
			gradTrainingList.add(gradTrainining);
		}
		return gradTrainingList;
	}

	@Override
	public GraduateTraining getGraduateTrainingByLocation(String token,
			String location) {
		try {
			PreparedStatement select = connection.
					prepareStatement(HRConstants.SELECT_GRAD_TRAINING_BY_LOCATION);
			select.setString(0, location);
			ResultSet result = select.executeQuery();
			select.close();
			if(result.next()) {
				GraduateTraining gradTraining = new GraduateTraining(
						result.getInt(HRConstants.GRAD_TRAINING_ID),
						result.getString(HRConstants.GRAD_TRAINING_LOCATION),
						result.getDate(HRConstants.GRAD_TRAINING_STARTS),
						result.getDate(HRConstants.GRAD_TRAINING_ENDS));
				return gradTraining;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
