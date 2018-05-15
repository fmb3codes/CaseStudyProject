import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.OrdersExceptionHandling;
import classes.DatabaseConnection;
import classes.Orders;
import classes.OrdersServices;

public class Mummy {

	public static void main(String[] args) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
	    Date now = new Date();
	    String today = sdfDate.format(now);
	    
		DatabaseConnection db = new DatabaseConnection();
		Orders order = new Orders();
		OrdersServices orderService = new OrdersServices(db);
		
		try
		{
			
			orderService.setNewOrderID();
			order.setOrder_id("1000005");
			order.setCustomer_location("1000001");
			order.setCustomer_id("1000001");
			order.setOrder_status("1000001");
			order.setOrder_date(today);
			order.setDelivery_date("15-03-2018");
			order.setOrder_on_hold(true);
			order.setTimes_changes(0);
			
			orderService.addNewOrder(order);
			//orderService.deleteOrder(order);
			orderService.getAllOrders();
			//orderService.getSpecificOrder(order);
			
			db.getConnection().close();
		}
		
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(OrdersExceptionHandling e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}
