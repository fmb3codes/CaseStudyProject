package classes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersServices 
{
	private  DatabaseConnection db_connection;
	private String nextOrderID;
	
	public void setNewOrderID() throws SQLException, Exception
	{
		this.nextOrderID = findNextOrderId();
	}
	
	public String getNewOrderID()
	{
		return this.nextOrderID;
	}
	
	public OrdersServices(DatabaseConnection db)
	{
		this.db_connection = db;
	}
	
	public String findNextOrderId() throws SQLException, Exception
	{
		ResultSet oraResult = db_connection.getStatement().executeQuery("SELECT SEQ_ORDERS.nextval id FROM dual");
		oraResult.next();
		return oraResult.getString("id");
	}
	
	public void addNewOrder(Orders order) throws SQLException, Exception
	{
		
		CallableStatement oraCallStmtfn = db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_ORDER(?,?,?,?,?,?,?)}");
		
		//oraCallStmtfn.setString(1, order.getOrder_id());
		oraCallStmtfn.setString(1, order.getOrder_date());
		oraCallStmtfn.setString(2, order.getDelivery_date());
		oraCallStmtfn.setBoolean(3, order.isOrder_on_hold());
		oraCallStmtfn.setInt(4, order.getTimes_changes());
		oraCallStmtfn.setString(5, order.getCustomer_id());
		oraCallStmtfn.setString(6, order.getOrder_status());
		oraCallStmtfn.setString(7, order.getCustomer_location());
		oraCallStmtfn.executeUpdate();
		System.out.println("Procedure Executed");
		System.out.println("A new order has been succesfully added");
	}
	
	public void deleteOrder(Orders order) throws SQLException, Exception
	{
		CallableStatement oraCallStmtfn = db_connection
				.getConnection()
				.prepareCall("{call SP_DELETE_ORDER(?)}");
		
		oraCallStmtfn.setString(1, order.getOrder_id());
		oraCallStmtfn.executeUpdate();
		System.out.println("Order " + order.getOrder_id() + " has been succesfully deleted");
	}
	
	public void getAllOrders() throws SQLException, Exception
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
	
	public void getSpecificOrder(Orders order) throws SQLException, Exception
	{
		PreparedStatement oraPrepStmt = db_connection.getConnection().prepareStatement(
				"SELECT * FROM ORDERS WHERE O_ID = ?");
		oraPrepStmt.setString(1, order.getOrder_id());
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


