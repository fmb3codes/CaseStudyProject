 package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.DatabaseConnection;
import classes.Orders;
import interfaces.DatabaseServices;

public class OrderServices implements DatabaseServices
{
	private DatabaseConnection db_connection;
	private Orders order;
	
	//Constructor
	
	public OrderServices(DatabaseConnection db, Orders order)
	{
		this.setDatabaseConnection(db);
		this.setOrder(order);
	}
	
	//Setters
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	private void setOrder(Orders order) 	  				  {this.order = order;}
	
	//DatabaseService Interface Methods
	
	@Override
	public void Create() throws SQLException, Exception
	{
		CallableStatement oraCallStmt = db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_ORDER(?,?,?,?,?,?,?)}");
		oraCallStmt.setString(1, order.getOrder_date());
		oraCallStmt.setString(2, order.getDelivery_date());
		oraCallStmt.setBoolean(3, order.isOrder_on_hold());
		oraCallStmt.setInt(4, order.getTimes_changes());
		oraCallStmt.setString(5, order.getCustomer_id());
		oraCallStmt.setString(6, order.getOrder_status());
		oraCallStmt.setString(7, order.getCustomer_location());
		oraCallStmt.execute();
		System.out.println("Procedure Executed");
		System.out.println("A new order has been succesfully added");
	}

	@Override
	public void Update() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() throws SQLException, Exception
	{
		CallableStatement oraCallStmt = db_connection
		.getConnection()
		.prepareCall("{call SP_DELETE_ORDER(?)}");

		oraCallStmt.setString(1, order.getOrder_id());
		oraCallStmt.execute();
		System.out.println("Order " + order.getOrder_id() + " has been succesfully deleted");
	}

	@Override
	public void GetAll() throws SQLException, Exception 
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

	@Override
	public void Find() throws SQLException, Exception
	{
		PreparedStatement oraPrepStmt = db_connection.getConnection().prepareStatement("SELECT * FROM ORDERS WHERE O_ID = ?");
		oraPrepStmt.setString(1, order.getOrder_id());
		ResultSet result = oraPrepStmt.executeQuery();
		String format = "|%1$-3s|%2$-10s|%3$-10s|%4$-5s|%5$-3s|%6$-3s|%7$-3s|%8$-3s|\n";
		if(result.isBeforeFirst())
		{
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
		}
		else
			System.out.println("Order ID: " + order.getOrder_id() + " was not found");
	}
	

}


