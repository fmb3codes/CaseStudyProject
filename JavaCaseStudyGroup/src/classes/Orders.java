package classes;
import exceptions.OrdersExceptionHandling;

public class Orders 
{
	private String order_id;
	private String customer_id;
	private String order_date;
	private String order_status;
	private String customer_location;
	private String delivery_date;
	private boolean order_on_hold;
	private int times_changes;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) throws OrdersExceptionHandling
	{
		if(order_id.length() < 7)
			throw new OrdersExceptionHandling("Order ID must be at least 7 characters long");
		else
			this.order_id = order_id;
	}
	public String getCustomer_id() 
	{
		return customer_id;
	}
	public void setCustomer_id(String customer_id) throws OrdersExceptionHandling 
	{
		if(customer_id.length() < 7)
			throw new OrdersExceptionHandling("Customer ID must be at least 7 characters long");
		else
			this.customer_id = customer_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) throws OrdersExceptionHandling
	{
		
		if(order_date == null)
			throw new OrdersExceptionHandling("Order Date cannot be null");
		else
			this.order_date = order_date;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) throws OrdersExceptionHandling 
	{
		if(order_status.length() < 7)
			throw new OrdersExceptionHandling("Order Status ID must be at least 7 characters long");
		else
			this.order_status = order_status;
	}
	public String getCustomer_location() {
		return customer_location;
	}
	
	public void setCustomer_location(String customer_location) throws OrdersExceptionHandling 
	{
		if(customer_location.length() < 7)
			throw new OrdersExceptionHandling("Customer Location ID must be at least 7 characters long");
		else
			this.customer_location = customer_location;
	}
	
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) throws OrdersExceptionHandling 
	{
		if(delivery_date == null)
			throw new OrdersExceptionHandling("Delivery Date cannot be null");
		else
			this.delivery_date = delivery_date;
	}
	public boolean isOrder_on_hold() 
	{
		return order_on_hold;
	}
	public void setOrder_on_hold(boolean order_on_hold)
	{
		this.order_on_hold = order_on_hold;
	}
	public int getTimes_changes() 
	{
		return times_changes;
	}
	public void setTimes_changes(int times_changes)
	{
		this.times_changes = times_changes;
	}
	
	public Orders()
	{
		super();
	}
	
	public Orders(String order_id, String customer_id, String order_date, String order_status, String customer_location,
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


