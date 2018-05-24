package classes;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.OrderException;

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
	private final int MINIMUM_ID_LENGTH = 7;
	
	final static SimpleDateFormat fDate = new SimpleDateFormat("dd-MM-yyyy");
	final static Date now = new Date();
	final static String today = fDate.format(now);
	
	//Constructor
	public Orders() {
		this.order_status = "1000001";  
		this.order_on_hold = false; 
		this.times_changes = 0; 
		this.order_date = today; 
	}
	
	public Orders(String customer_id, String order_date, String order_status, String customer_location,
			String delivery_date, boolean order_on_hold, int times_changes) throws OrderException
	{
		this.setCustomer_id(customer_id);
		this.setOrder_date(order_date);
		this.setOrder_status(order_status);
		this.setCustomer_location(customer_location);
		this.setDelivery_date(delivery_date);
		this.setOrder_on_hold(order_on_hold);
		this.setTimes_changes(times_changes);
	}
	
	//Getters
	
	public String getOrder_id(){
		
		
		return order_id;
		
	}
	
	public String getCustomer_id() 		 {return customer_id;}
	public String getOrder_date()  		 {return order_date;}
	public String getOrder_status() 	 {return order_status;}
	public String getCustomer_location() {return customer_location;}
	public String getDelivery_date() 	 {return delivery_date;}
	public int getTimes_changes() 		 {return times_changes;}
	
	//Setters
	
	public void setOrder_id(String order_id) throws OrderException
	{
		if(order_id.length() < MINIMUM_ID_LENGTH)
			throw new OrderException("Order ID must be at least " + MINIMUM_ID_LENGTH + " characters long");
		else
			this.order_id = order_id;
	}
	
	public void setCustomer_id(String customer_id) throws OrderException 
	{
		if(customer_id.length() < MINIMUM_ID_LENGTH)
			throw new OrderException("Customer ID must be at least " + MINIMUM_ID_LENGTH + " characters long");
		else
			this.customer_id = customer_id;
	}
	
	public void setOrder_date(String order_date) throws OrderException
	{
		if(order_date == null)
			throw new OrderException("Order Date cannot be null");
		else
			this.order_date = order_date;
	}
	
	public void setOrder_status(String order_status) throws OrderException 
	{
		if(order_status.length() < MINIMUM_ID_LENGTH)
			throw new OrderException("Order Status ID must be at least " + MINIMUM_ID_LENGTH + " characters long");
		else
			this.order_status = order_status;
	}
	
	
	public void setCustomer_location(String customer_location) throws OrderException 
	{
		if(customer_location.length() < MINIMUM_ID_LENGTH)
			throw new OrderException("Customer Location ID must be at least " + MINIMUM_ID_LENGTH + " characters long");
		else
			this.customer_location = customer_location;
	}
	
	
	public void setDelivery_date(String delivery_date) throws OrderException 
	{
		if(delivery_date == null)
			throw new OrderException("Delivery Date cannot be null");
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
	
	public void setTimes_changes(int times_changes)
	{
		this.times_changes = times_changes;
	}
}


