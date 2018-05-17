package classes;


public class CustomersCreditCards {
	
	private String C_ID;
	private String CC_ID;
	/**
	 * @param c_ID
	 * @param cC_ID
	 */
	public CustomersCreditCards(String c_ID, String cC_ID) {
		super();
		C_ID = c_ID;
		CC_ID = cC_ID;
	}
	/**
	 * @return the c_ID
	 */
	public String getC_ID() {
		return C_ID;
	}
	/**
	 * @param c_ID the c_ID to set
	 */
	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}
	/**
	 * @return the cC_ID
	 */
	public String getCC_ID() {
		return CC_ID;
	}
	/**
	 * @param cC_ID the cC_ID to set
	 */
	public void setCC_ID(String cC_ID) {
		CC_ID = cC_ID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customers_Credit_Cards [C_ID=" + C_ID + ", CC_ID=" + CC_ID + "]";
	}

}
