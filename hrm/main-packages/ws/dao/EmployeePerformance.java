/**
 * 
 */
package ws.dao;

/**
 * @author Kristiyan
 *
 */
public class EmployeePerformance {
	private int perfId;
	private int employeeId;
	private String perfDescription;
	private int perfYear;
	
	public EmployeePerformance() {}

	/**
	 * @param perfId
	 * @param employeeId
	 * @param perfDescription
	 * @param perfYear
	 */
	public EmployeePerformance(int perfId, int employeeId,
			String perfDescription, int perfYear) {
		this.perfId = perfId;
		this.employeeId = employeeId;
		this.perfDescription = perfDescription;
		this.perfYear = perfYear;
	}

	/**
	 * @return the perfId
	 */
	public int getPerfId() {
		return perfId;
	}

	/**
	 * @param perfId the perfId to set
	 */
	public void setPerfId(int perfId) {
		this.perfId = perfId;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the perfDescription
	 */
	public String getPerfDescription() {
		return perfDescription;
	}

	/**
	 * @param perfDescription the perfDescription to set
	 */
	public void setPerfDescription(String perfDescription) {
		this.perfDescription = perfDescription;
	}

	/**
	 * @return the perfYear
	 */
	public int getPerfYear() {
		return perfYear;
	}

	/**
	 * @param perfYear the perfYear to set
	 */
	public void setPerfYear(int perfYear) {
		this.perfYear = perfYear;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeePerformance [perfId=" + perfId + ", employeeId="
				+ employeeId + ", perfDescription=" + perfDescription
				+ ", perfYear=" + perfYear + "]";
	}
	
}
