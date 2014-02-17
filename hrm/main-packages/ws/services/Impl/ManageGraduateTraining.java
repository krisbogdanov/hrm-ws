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

import ws.dao.Employee;
import ws.dao.GraduateTraining;
import ws.security.AuthorizationManager;
import ws.security.InputValidationManager;
import ws.security.Impl.AuthorizationManagerImpl;
import ws.security.Impl.InputValidationManagerImpl;
import ws.services.IManageGraduateTraining;
import ws.utils.Impl.DatabaseConnection;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class ManageGraduateTraining implements IManageGraduateTraining {
	private final Connection connection = DatabaseConnection.getConnection();
	private final AuthorizationManager authManager = new AuthorizationManagerImpl();
	private final InputValidationManager validationManager = new InputValidationManagerImpl();
	@Override
	public int addGraduateTraining(String token, String location, Date starts,
			Date ends, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(location) ||
						!validationManager.dateValidation(starts) ||
						!validationManager.dateValidation(ends)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				GraduateTraining gradTraining = getGraduateTrainingByLocation(token, location, secure);
				if(gradTraining != null) {
					return 0;
				} else {
					if(starts.equals(ends)) {
						return 0;
					} else {
						PreparedStatement insertStatement = connection.
								prepareStatement(HRConstants.INSERT_GRAD_TRAINING_STATEMENT);
						insertStatement.setString(1, location);
						insertStatement.setObject(2, starts);
						insertStatement.setObject(3, ends);
						int result = insertStatement.executeUpdate();
						insertStatement.close();
						if(result == 0) {
							return 0;
						} else {
							return 1;
						}
					}
				}
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int removeGraduateTrainigByLocation(String token, String location, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(location)) {
					return 0;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.WRITE)) {
				GraduateTraining gradTraining = getGraduateTrainingByLocation(token, location, secure);
				
				PreparedStatement deleteStatement = connection.
						prepareStatement(HRConstants.REMOVE_GRAD_TRAINING_BY_LOCATION);
				deleteStatement.setString(1, location);
				int result = deleteStatement.executeUpdate();
				PreparedStatement deleteMappings = connection.
						prepareStatement(HRConstants.REMOVE_GRAD_TRAINING_MAPPINGS);
				deleteMappings.setInt(1, gradTraining.getGradTrainingId());
				deleteMappings.executeUpdate();
				deleteMappings.close();
				deleteStatement.close();
				if(result == 0) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}


	@Override
	public List<GraduateTraining> getAllGraduateTraining(String token, boolean secure) {
		try {
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
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
			} else {
				return null;
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
			String location, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.textValidation(location)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement select = connection.
						prepareStatement(HRConstants.SELECT_GRAD_TRAINING_BY_LOCATION);
				select.setString(1, location);
				ResultSet result = select.executeQuery();
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
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> getEmployeesRegisteredForGradTraining(String token,
			int gradTrainingId, boolean secure) {
		try {
			if(secure) {
				if(!validationManager.integerValidation(gradTrainingId)) {
					return null;
				}
			}
			if(authManager.isAuthorizedTo(token, HRConstants.READ)) {
				PreparedStatement selectMappings = connection.
						prepareStatement(HRConstants.SELECT_EMP_TO_GRAD_MAPPING_BY_GRAD_ID);
				selectMappings.setInt(1, gradTrainingId);
				ResultSet result = selectMappings.executeQuery();
				List<Employee> list = new ArrayList<Employee>();
				while(result.next()) {
					int employeeId = result.getInt(HRConstants.EMPLOYEE_ID);
					PreparedStatement selectEmp = connection.
							prepareStatement(HRConstants.SELECT_EMPLOYEE_BY_ID);
					selectEmp.setInt(1, employeeId);
					ResultSet empResult = selectEmp.executeQuery();
					if(empResult.next()) {
						Employee employee = new Employee(
								empResult.getInt(HRConstants.EMPLOYEE_ID),
								empResult.getString(HRConstants.EMPLOYEE_NAME), 
								empResult.getString(HRConstants.EMPLOYEE_SURNAME), 
								empResult.getString(HRConstants.EMPLOYEE_EMAIL), 
								empResult.getString(HRConstants.EMPLOYEE_ADDRESS), 
								empResult.getString(HRConstants.EMPLOYEE_SSN), 
								empResult.getString(HRConstants.EMPLOYEE_USERNAME), 
								empResult.getString(HRConstants.EMPLOYEE_PASSWORD), 
								empResult.getDate(HRConstants.EMPLOYEE_JOINED), 
								empResult.getInt(HRConstants.EMPLOYEE_DEPARTMENT), 
								empResult.getString(HRConstants.EMPLOYEE_PHONE),
								empResult.getString(HRConstants.EMPLOYEE_ROLE));
						list.add(employee);
					}
				}
				return list;
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
