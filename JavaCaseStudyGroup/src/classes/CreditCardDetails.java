package classes;


public class CreditCardDetails {

	
	private String P_ID;
	private String CC_ID;
	private String O_ID;
	/**
	 * @return the p_ID
	 */
	public String getP_ID() {
		return P_ID;
	}
	/**
	 * @param p_ID the p_ID to set
	 */
	public void setP_ID(String p_ID) {
		P_ID = p_ID;
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
	/**
	 * @return the o_ID
	 */
	public String getO_ID() {
		return O_ID;
	}
	/**
	 * @param o_ID the o_ID to set
	 */
	public void setO_ID(String o_ID) {
		O_ID = o_ID;
	}
	/**
	 * @param p_ID
	 * @param cC_ID
	 * @param o_ID
	 */
	public CreditCardDetails(String p_ID, String cC_ID, String o_ID) {
		super();
		P_ID = p_ID;
		CC_ID = cC_ID;
		O_ID = o_ID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Credit_Card_Details [P_ID=" + P_ID + ", CC_ID=" + CC_ID + ", O_ID=" + O_ID + "]";
	}
	/**
	 * 
	 */
	public CreditCardDetails() {
		super();
	}
	
	
}
