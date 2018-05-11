package case_study_classes;

public class Orders 
{
	private int order_id;
	private int customer_id;
	private String order_date;
	private int order_status;
	private int customer_location;
	private String delivery_date;
	private boolean order_on_hold;
	private int times_changes;
	
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public int getCustomer_location() {
		return customer_location;
	}
	public void setCustomer_location(int customer_location) {
		this.customer_location = customer_location;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public boolean isOrder_on_hold() {
		return order_on_hold;
	}
	public void setOrder_on_hold(boolean order_on_hold) {
		this.order_on_hold = order_on_hold;
	}
	public int getTimes_changes() {
		return times_changes;
	}
	public void setTimes_changes(int times_changes) {
		this.times_changes = times_changes;
	}
	
	public Orders()
	{
		super();
	}
	
	public Orders(int order_id, int customer_id, String order_date, int order_status, int customer_location,
			String delivery_date, boolean order_on_hold, int times_changes)
	{
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.order_date = order_date;
		this.order_status = order_status;
		this.customer_location = customer_location;
		this.delivery_date = delivery_date;
		this.order_on_hold = order_on_hold;
		this.times_changes = times_changes;
	}
	
	
	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", customer_id=" + customer_id + ", order_date=" + order_date
				+ ", order_status=" + order_status + ", customer_location=" + customer_location + ", delivery_date="
				+ delivery_date + ", order_on_hold=" + order_on_hold + ", times_changes=" + times_changes + "]";
	}
	
	
	
	
}


