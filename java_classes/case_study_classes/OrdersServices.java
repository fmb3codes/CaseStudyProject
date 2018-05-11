package case_study_classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdersServices 
{
	private  DatabaseConnection db_connection;
	
	public OrdersServices(DatabaseConnection db)
	{
		this.db_connection = db;
	}
	
	public void addNewOrder(Orders order) throws Exception 
	{
		PreparedStatement oraPrepStmt = 
		db_connection.getConnection().prepareStatement(""
				+ "INSERT INTO ORDERS VALUES(?,to_date(?,'dd-mm-yyyy'),"
				+ "to_date(?,'dd-mm-yyyy'),?,?,?,?,?)");
		
		oraPrepStmt.setInt(1, order.getOrder_id());
		oraPrepStmt.setString(2, order.getOrder_date());
		oraPrepStmt.setString(3, order.getDelivery_date());
		oraPrepStmt.setBoolean(4, order.isOrder_on_hold());
		oraPrepStmt.setInt(5, order.getTimes_changes());
		oraPrepStmt.setInt(6, order.getCustomer_id());
		oraPrepStmt.setInt(7, order.getOrder_status());
		oraPrepStmt.setInt(8, order.getCustomer_location());
		oraPrepStmt.executeUpdate();
		System.out.println("A new order has been succesfully added");
		db_connection.getConnection().close();
	}
	
	public void deleteOrder(Orders order) throws Exception
	{
		PreparedStatement oraPrepStmt = 
				db_connection.getConnection().prepareStatement(""
						+ "DELETE FROM ORDERS WHERE O_ID = ?");
		oraPrepStmt.setInt(1, order.getOrder_id());
		oraPrepStmt.executeUpdate();
		System.out.println("Order " + order.getOrder_id() + " has been succesfully deleted");
	}
	
	public void getAllOrders() throws Exception
	{
		ResultSet oraResult = db_connection.getStatement().executeQuery("SELECT * FROM ORDERS");
		String format = "|%1$-3s|%2$-10s|%3$-10s|%4$-5s|%5$-3s|%6$-3s|%7$-3s|%8$-3s|\n";
		while(oraResult.next())
		{
			System.out.format(
					format,
					oraResult.getInt("O_ID"), 
					oraResult.getDate("ORDER_DATE"), 
					oraResult.getDate("DELIVERY_DATE"),
					oraResult.getBoolean("ORDER_ON_HOLD"),
					oraResult.getInt("TIMES_CHANGED"),
					oraResult.getInt("C_ID"),
					oraResult.getInt("OS_ID"),
					oraResult.getInt("CL_ID")
			);
		}
	}
	
	public void getSpecificOrder(Orders order) throws Exception
	{
		PreparedStatement oraPrepStmt = db_connection.getConnection().prepareStatement(
				"SELECT * FROM ORDERS WHERE O_ID = ?");
		oraPrepStmt.setInt(1, order.getOrder_id());
		ResultSet result = oraPrepStmt.executeQuery();
		String format = "|%1$-3s|%2$-10s|%3$-10s|%4$-5s|%5$-3s|%6$-3s|%7$-3s|%8$-3s|\n";
		while(result.next())
		{
			System.out.format(
					format,
					result.getInt("O_ID"), 
					result.getDate("ORDER_DATE"), 
					result.getDate("DELIVERY_DATE"),
					result.getBoolean("ORDER_ON_HOLD"),
					result.getInt("TIMES_CHANGED"),
					result.getInt("C_ID"),
					result.getInt("OS_ID"),
					result.getInt("CL_ID")
			);
		}
		
		System.out.println("Succesfully retrieved order #" + order.getOrder_id());
	}
	
	public void updateOrder(Orders order)
	{
		
	}
	
	
	
}


