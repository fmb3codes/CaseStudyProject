import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.OrderException;

import services.OrderServices;
import classes.DatabaseConnection;
import classes.Orders;


public class Mummy {

	public static void main(String[] args) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
	    Date now = new Date();
	    String today = sdfDate.format(now);
	    
		DatabaseConnection db = new DatabaseConnection();
		Orders order = new Orders();
		
		
		try
		{
			OrderServices orderService = new OrderServices(db, order);
			order.setOrder_id("1000005");
			order.setCustomer_location("1000012");
			order.setCustomer_id("1000003");
			order.setOrder_status("1000002");
			order.setOrder_date(today);
			order.setDelivery_date("15-03-2018");
			order.setOrder_on_hold(true);
			order.setTimes_changes(0);
			
			//args.orderService.addNewOrder(order);
			//orderService.deleteOrder(order);
			//orderService.getAllOrders();
			//orderService.getSpecificOrder(order);
			
			db.getConnection().close();
		}
		
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(OrderException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}
