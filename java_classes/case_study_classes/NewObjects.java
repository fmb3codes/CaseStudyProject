package case_study_classes;

import java.text.SimpleDateFormat;
import java.util.Date;
public class NewObjects {

	
	public static void main(String[] args) 
	{
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
	    Date now = new Date();
	    String today = sdfDate.format(now);

		DatabaseConnection db = new DatabaseConnection();
		Orders order = new Orders();
		OrdersServices orderService = new OrdersServices(db);
		
		order.setOrder_id(1);
		order.setCustomer_location(1);
		order.setCustomer_id(1);
		order.setOrder_status(1);
		order.setOrder_date(today);
		order.setDelivery_date("15-05-2018");
		order.setOrder_on_hold(false);
		order.setTimes_changes(0);
		
		//System.out.println(order.toString());
		
		try
		{
			//orderService.addNewOrder(order);
			//orderService.deleteOrder(order);
			//orderService.getAllOrders();
			orderService.getSpecificOrder(order);
			
			db.getConnection().close();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
