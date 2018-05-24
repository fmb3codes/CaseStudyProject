package services;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;

import classes.Customer;
import classes.DatabaseConnection;
import classes.Meal;
import classes.Orders;
import interfaces.DatabaseServices;

public class OrderServices implements DatabaseServices
{
	private DatabaseConnection db_connection;
	private Orders order;
	private Customer customer;
	
	final static SimpleDateFormat fDate = new SimpleDateFormat("dd-MM-yyyy");
	final static Date now = new Date();
	final static String today = fDate.format(now);
	
	//Constructor
	
	public OrderServices()
	{
		
	}
	
	public OrderServices(DatabaseConnection db, Orders order)
	{
		this.setDatabaseConnection(db);
		this.setOrder(order);
	}
	
	public OrderServices(DatabaseConnection db, Orders order, Customer customer)
	{
		this.setDatabaseConnection(db);
		this.setOrder(order);
		this.customer = customer;
	}
	
	public OrderServices(DatabaseConnection db)
	{
		this.setDatabaseConnection(db);
		
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
	
	public String getOrderID(String id) throws SQLException, Exception
	{
		PreparedStatement oraPrepStmt = db_connection
				.getConnection()
				.prepareStatement("SELECT TEMP.O_ID FROM (SELECT O.*, ROW_NUMBER() "
						+ "OVER (ORDER BY O.O_ID DESC) R FROM ORDERS O) TEMP "
						+ "WHERE R=1 AND C_ID=?");
		oraPrepStmt.setString(1, id);
		ResultSet result = oraPrepStmt.executeQuery();
		result.next();
		return result.getString(1);
	}

	@Override
	public void Update() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void Delete(int selectOption) throws SQLException, Exception
//	{
//		CallableStatement oraCallStmt = db_connection
//		.getConnection()
//		.prepareCall("{call SP_DEL_NEW_Order(?)}");
//
//		oraCallStmt.setLong(1, selectOption);
//		oraCallStmt.execute();
//		System.out.println("Order " + selectOption + " has been succesfully deleted");
//	}

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
					String[] col_fields = new String[num_fields];
					//String[] col_names = new String[num_fields];
					
					for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
					    col_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
					    //col_names[i - 1] = meta_data.getColumnName(i);
					}		
					
					//System.out.println(meal_fields);
					System.out.println();
					
					for (String j:col_fields){
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
		
		public void displayForIDToUpdate(String custID) {
			
			DatabaseConnection site = new DatabaseConnection();
			Connection con = site.getConnection();
			
						
			try {
				PreparedStatement oracleStmt = con.prepareStatement("Select O_ID as \"Order ID\", ORDER_DATE as \"Order Date\", DELIVERY_DATE as \"Delivery Date\", ORDER_ON_HOLD as \"Order Status\" from Orders, Order_Status where Orders.OS_ID=Order_Status.OS_ID AND Order_Status.NAME = 'PENDING' AND C_ID=?");
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
					String[] col_fields = new String[num_fields];
						
					for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
					    col_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
					}		
					
					System.out.println();
					
					for (String j:col_fields){
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

		@Override
		public void Delete() throws SQLException, Exception 
		{
			CallableStatement oraCallStmt = db_connection
					.getConnection()
					.prepareCall("{call SP_DEL_NEW_Order(?,?)}");

					oraCallStmt.setString(1, order.getOrder_id());
					oraCallStmt.setString(2, order.getCustomer_id());
					oraCallStmt.execute();
					System.out.println("Order " + order.getOrder_id() + " has been succesfully deleted");
			
		}

		public ArrayList<String> getAllOrderIDs(String id) throws SQLException, Exception
		{
			ArrayList<String> ids = new ArrayList<String>();
			int k = 0;

			PreparedStatement oraResult = db_connection
					.getConnection()
					.prepareStatement("SELECT O_ID, DELIVERY_DATE, ORDER_ON_HOLD FROM ORDERS"
							+ " WHERE C_ID = ? ");
			oraResult.setString(1, id);
			ResultSet result = oraResult.executeQuery();
			

			while(result.next())
			{
				k++;
				System.out.println(k + " " + result.getString(2) + result.getString(3));
				ids.add(result.getString(1));
			}
				
			
			return ids;
		}

		public void produceInvoice() throws SQLException, Exception
		{
			String filename = order.getOrder_id() + "_" + today;
			String details = "";
			PreparedStatement oraResult = db_connection
					.getConnection()
					.prepareStatement("SELECT O.DELIVERY_DATE, M.NAME, M.DESCRIPTION, M.PRICE, OS.NAME, PT.PAYMENT_NAME FROM MEALS M "
							+ "JOIN ORDER_MEALS OM "
							+ "ON M.M_ID = OM.M_ID "
							+ "JOIN ORDERS O "
							+ "ON OM.O_ID = O.O_ID "
							+ "JOIN ORDER_STATUS OS "
							+ "ON O.OS_ID = OS.OS_ID "
							+ "JOIN PAYMENTS P "
							+ "ON P.O_ID = O.O_ID "
							+ "JOIN PAYMENT_TYPES PT "
							+ "ON P.PT_ID = PT.PT_ID "
							+ "WHERE O.O_ID = ?");
			
			oraResult.setString(1, order.getOrder_id());
			ResultSet result = oraResult.executeQuery();
			
			try
			{
				FileWriter fw = new FileWriter("C:\\invoices\\"+ filename + ".txt");
				fw.write("--------------------------------------------\r\n");
				fw.write("|             Mummy's Restaurant           |\r\n");
				fw.write("--------------------------------------------\r\n");
				
				fw.write("Order #:" + order.getOrder_id() + "\r\n");
				fw.write("Customer: " + customer.getfName() + "\r\n");
				fw.write("--------------------------------------------\r\n");
				while(result.next())
				{
					details += "Delivery date: " + result.getString(1) + "\r\n" + result.getString(2) + " Price: $" + result.getString(4) + "\r\n" + result.getString(3) + "\r\nStatus: " + result.getString(5) + "\r\nPaid with: " + result.getString(6);
					fw.write(details);
				}
				
				fw.write("\r\n--------------------------------------------\r\n");
				fw.write("|             Created by Syntinels          |\r\n");
				fw.write("--------------------------------------------\r\n");
				
				fw.close();
				
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		
		public static void updateDeliveryAddress(String order_id, String new_delivery_date) { // deal with exception later
			// do null check on input
			
		    DatabaseConnection site = new DatabaseConnection();
			Connection con = site.getConnection();
			
			PreparedStatement oraclePreparedStmt;
			try {
				oraclePreparedStmt = con.prepareStatement("update" + " orders set DELIVERY_DATE=TO_DATE(?,'dd-MM-yyyy') WHERE O_ID=?");
								
				oraclePreparedStmt.setString(1,  new_delivery_date);
				oraclePreparedStmt.setString(2,  order_id);
				oraclePreparedStmt.execute(); // could change to executeUpdate?
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				site.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		}
		
	

		}

		public void updateOrderHold(String order_id) {
			// could potentially combine below queries into one using sub queries
			DatabaseConnection site = new DatabaseConnection();
			Connection con = site.getConnection();
			
			PreparedStatement oraclePreparedStmt;
			try {
				oraclePreparedStmt = con.prepareStatement("select order_on_hold from orders where O_ID=?");
				
				oraclePreparedStmt.setString(1,  order_id);
				
				ResultSet oracleRs = oraclePreparedStmt.executeQuery(); 

				int order_hold = 0;
				
				while(oracleRs.next())
				{
					order_hold = oracleRs.getInt(1); // gets order hold value as int for easier manipulation
				}
				
				order_hold = 1 - order_hold; // flips from 0 to 1 or vice versa
				
				oraclePreparedStmt = con.prepareStatement("update orders set order_on_hold=? where O_ID=?");
				oraclePreparedStmt.setString(1,  Integer.toString(order_hold));
				oraclePreparedStmt.setString(2,  order_id);
				oraclePreparedStmt.execute();
	
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			try {
				site.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
		
			
}
		
		public int orderExists(String order_id) {
			DatabaseConnection site = new DatabaseConnection();
			Connection con = site.getConnection();
			int order_exists = 0;
			
			//System.out.println("Getting here");
			
			try {
				PreparedStatement oracleStmt = con.prepareStatement("Select COUNT(*) from Orders where o_id=?");

				// change to prepared
				//PreparedStatement stmt = con.prepareStatement("Select C_ID from Customers where email = ? and password = ?");
				oracleStmt.setString(1, order_id);
				//oracleRs.next();
				
				ResultSet oracleRs;
				oracleRs = oracleStmt.executeQuery();
				
				
				while(oracleRs.next()){
					order_exists = oracleRs.getInt(1); // will be == 0 if order exists
					
					//custID = oracleRs.getInt(1);
				}			
				
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			
			//System.out.println("Query successful");
			try {
				site.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return order_exists;
			
		}
		
}

