package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import classes.DatabaseConnection;
import interfaces.ServiceOperations;

public class OrderMealsServices implements ServiceOperations {

	public OrderMealsServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertToDB(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInDB(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFromDB(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayRecord(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayRecords() {
		// TODO Auto-generated method stub

	}
	
	public void displayForID(String custID) {
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
							
		try {
			PreparedStatement oracleStmt = con.prepareStatement("Select ORDERS.O_ID as \"Order ID\", MEAL_TYPES.NAME as \"Meal Type\", MEALS.NAME as \"Name of meal\", MEALS.DESCRIPTION as \"Meal description\", ORDER_MEALS.QUANTITY as \"Quantity\" from CUSTOMERS, ORDERS, ORDER_MEALS, MEALS, MEAL_TYPES where CUSTOMERS.C_ID=ORDERS.C_ID AND ORDERS.O_ID=ORDER_MEALS.O_ID AND ORDER_MEALS.M_ID=MEALS.M_ID AND MEALS.MT_ID=MEAL_TYPES.MT_ID AND CUSTOMERS.C_ID=?");
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

}
