/**
 * 
 */
package ws.dao;

/**
 * @author Kristiyan
 *
 */
public class BankDetails {
	private int bankDetailsId;
	private int employeeId;
	private String bankName;
	private int accountNumber;
	private int sortCode;
	
	public BankDetails() {}

	/**
	 * @param bankDetailsId
	 * @param employeeId
	 * @param bankName
	 * @param accountNumber
	 * @param sortCode
	 */
	public BankDetails(int bankDetailsId, int employeeId, String bankName,
			int accountNumber, int sortCode) {
		this.bankDetailsId = bankDetailsId;
		this.employeeId = employeeId;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.sortCode = sortCode;
	}

	/**
	 * @return the bankDetailsId
	 */
	public int getBankDetailsId() {
		return bankDetailsId;
	}

	/**
	 * @param bankDetailsId the bankDetailsId to set
	 */
	public void setBankDetailsId(int bankDetailsId) {
		this.bankDetailsId = bankDetailsId;
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
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the sortCode
	 */
	public int getSortCode() {
		return sortCode;
	}

	/**
	 * @param sortCode the sortCode to set
	 */
	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BankDetails [bankDetailsId=" + bankDetailsId + ", employeeId="
				+ employeeId + ", bankName=" + bankName + ", accountNumber="
				+ accountNumber + ", sortCode=" + sortCode + "]";
	}
	
	
}
