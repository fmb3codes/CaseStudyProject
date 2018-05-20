package classes;

public class Payments
{
	private String paymentType;
	private String orderID;
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public Payments()
	{
	
	}
	public Payments(String paymentType, String orderID) {
		
		this.paymentType = paymentType;
		this.orderID = orderID;
	}
	
	
}
