package classes;

import exceptions.PaymentTypeExceptions;

public class PaymentTypes {
	
	private String PT_ID;
	private String PaymentName;
	/**
	 * @return the pT_ID
	 */
	public String getPT_ID() {
		return PT_ID;
	}
	/**
	 * @param pT_ID the pT_ID to set
	 * @throws PaymentTypeExceptions 
	 */
	public void setPT_ID(String PT_ID) throws PaymentTypeExceptions {

		if(PT_ID.length() < 7)
			throw new PaymentTypeExceptions("Payment ID must be at least 7 characters long");
		else
			this.PT_ID = PT_ID;	}
	/**
	 * @return the paymentName
	 */
	public String getPaymentName() {
		return PaymentName;
	}
	/**
	 * @param paymentName the paymentName to set
	 */
	public void setPaymentName(String paymentName) {
		PaymentName = paymentName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentTypes [PT_ID=" + PT_ID + ", PaymentName=" + PaymentName + "]";
	}
	/**
	 * @param pT_ID
	 * @param paymentName
	 */
	public PaymentTypes(String pT_ID, String paymentName) {
		super();
		this.PT_ID = pT_ID;
		this.PaymentName = paymentName;
	}
	
	

}
