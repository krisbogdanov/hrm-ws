/**
 * 
 */
package ws.dao;

import java.util.Date;

/**
 * @author Kristiyan
 *
 */
public class GraduateTraining {
	private int gradTrainingId;
	private String gradTrainingLocation;
	private Date gradTrainingStarts;
	private Date gradTrainingEnds;
	
	public GraduateTraining() {}

	/**
	 * @param gradTrainingId
	 * @param gradTrainingLocation
	 * @param gradTrainingStarts
	 * @param gradTrainingEnds
	 */
	public GraduateTraining(int gradTrainingId, String gradTrainingLocation,
			Date gradTrainingStarts, Date gradTrainingEnds) {
		this.gradTrainingId = gradTrainingId;
		this.gradTrainingLocation = gradTrainingLocation;
		this.gradTrainingStarts = gradTrainingStarts;
		this.gradTrainingEnds = gradTrainingEnds;
	}

	/**
	 * @return the gradTrainingId
	 */
	public int getGradTrainingId() {
		return gradTrainingId;
	}

	/**
	 * @param gradTrainingId the gradTrainingId to set
	 */
	public void setGradTrainingId(int gradTrainingId) {
		this.gradTrainingId = gradTrainingId;
	}

	/**
	 * @return the gradTrainingLocation
	 */
	public String getGradTrainingLocation() {
		return gradTrainingLocation;
	}

	/**
	 * @param gradTrainingLocation the gradTrainingLocation to set
	 */
	public void setGradTrainingLocation(String gradTrainingLocation) {
		this.gradTrainingLocation = gradTrainingLocation;
	}

	/**
	 * @return the gradTrainingStarts
	 */
	public Date getGradTrainingStarts() {
		return gradTrainingStarts;
	}

	/**
	 * @param gradTrainingStarts the gradTrainingStarts to set
	 */
	public void setGradTrainingStarts(Date gradTrainingStarts) {
		this.gradTrainingStarts = gradTrainingStarts;
	}

	/**
	 * @return the gradTrainingEnds
	 */
	public Date getGradTrainingEnds() {
		return gradTrainingEnds;
	}

	/**
	 * @param gradTrainingEnds the gradTrainingEnds to set
	 */
	public void setGradTrainingEnds(Date gradTrainingEnds) {
		this.gradTrainingEnds = gradTrainingEnds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GraduateTraining [gradTrainingId=" + gradTrainingId
				+ ", gradTrainingLocation=" + gradTrainingLocation
				+ ", gradTrainingStarts=" + gradTrainingStarts
				+ ", gradTrainingEnds=" + gradTrainingEnds + "]";
	}
	
}
