package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import classes.DatabaseConnection;
import classes.Orders;
import interfaces.DatabaseServices;

public class OrderServices implements DatabaseServices
{
	private DatabaseConnection db_connection;
	private Orders order;
	
	//Constructor
	
	public OrderServices()
	{
		
	}
	
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
	
	// modify to print fields only if a record is found?
		public void displayForID(String custID) {
			
			DatabaseConnection site = new DatabaseConnection();
			Connection con = site.getConnection();
			
						
			try {
				PreparedStatement oracleStmt = con.prepareStatement("Select O_ID as \"Order ID\", ORDER_DATE as \"Order Date\", DELIVERY_DATE as \"Delivery Date\", ORDER_ON_HOLD as \"Order Status\" from Orders where C_ID=?");
				oracleStmt.setString(1, custID);
				
				
				// NEED to parse order_on_hold to properly display status
				
				ResultSet oracleRs = oracleStmt.executeQuery();
				
				int num_fields = 0;
				ResultSetMetaData meta_data;
				
				if (oracleRs != null) {
					meta_data = oracleRs.getMetaData();
					num_fields = meta_data.getColumnCount();
					
					String[] col_names = new String[num_fields];

					
					for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
					    col_names[i - 1] = meta_data.getColumnName(i);
					}	
					
					// add if statement to only print if a record was found
					for (String j:col_names){
						System.out.print("| " + j + " ");
					}
					
				}
				
				
				while (oracleRs.next()) {
					//System.out.println(oracleRs.getString("first_name") + " " + oracleRs.getString(2) + " " + oracleRs.getInt("salary"));
					//System.out.println(oracleRs.getString("*"));
					
					//System.out.println("Meta data is: ");
					//System.out.println(oracleRs.getMetaData().getColumnCount());
					
					//ResultSetMetaData meta_data = oracleRs.getMetaData();
					//int num_fields = meta_data.getColumnCount();
					String[] meal_fields = new String[num_fields];
					//String[] col_names = new String[num_fields];
					
					for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
					    meal_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
					    //col_names[i - 1] = meta_data.getColumnName(i);
					}		
					
					//System.out.println(meal_fields);
					System.out.println();
					
					for (String j:meal_fields){
						System.out.print("| " + j + " ");
					}
					
				
				}
				
			}
			catch (Exception ex){
				ex.printStackTrace();
			}

			
			System.out.println("\nQuery Successful");
			
			try {
				site.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	

}


