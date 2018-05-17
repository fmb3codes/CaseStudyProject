package classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import interfaces.ServiceOperations;

public class MealServices implements ServiceOperations {
	
	public static void main(String[] args) {
		
		MealServices service = new MealServices();
		
		Meal testMeal1 = new Meal("1000001", "some bread", "bread", 2.4, "img_path", "15-8-1950", "1000007");

		//Meal testMeal1 = new Meal(2, "some soup", "Soup", 1.2, "img_path", "11-22-1923", 5);
		//Customer testCustomer2 = new Customer(5, "Randy", "Manlet", "ranlet@gmail.com", "pass123", getCurrentTimeStamp(), 123, 143);
		
		//Customer testCustomer3 = new Customer(2, "Randy", "Manlet", "ranlet@gmail.com", "pass123", getCurrentTimeStamp(), 123, 143);

		// sync?
		//service.addMealSP(testMeal1);
		//service.addCustomer(testCustomer2);
		//service.viewCustomer(testCustomer1);
		//service.deleteCustomer(testCustomer2);
		//service.viewCustomer(testCustomer1);
		//service.updateCustomer(new Customer(5, "Randy", "Manletter", "ranlet@gmail.com", "pass123", getCurrentTimeStamp(), 123, 150));
		//service.viewCustomer(testCustomer2);
		//service.addCustomerSP(testCustomer1);
		//service.deleteCustomerSP(testCustomer1);
		service.displayRecords();
		
	}

	public MealServices() {
		// TODO Auto-generated constructor stub
	}
	
	public String findNextMealId() throws SQLException, Exception
	{
		DatabaseConnection site = new DatabaseConnection();
		
		ResultSet oraResult = site.getStatement().executeQuery("SELECT SEQ_MEALS.nextval id FROM dual");
		oraResult.next();
		return oraResult.getString("id");
	}
	
	public void insertToDB(Object mealToAdd) { // stored procedure
		String ID = ((Meal) mealToAdd).getID();
		String description = ((Meal) mealToAdd).getDescription();
		String name = ((Meal) mealToAdd).getName();
		Double price = ((Meal) mealToAdd).getPrice();
		String imagePath = ((Meal) mealToAdd).getImagePath();
	    String mealOfDay = ((Meal) mealToAdd).getMealOfDay();
	    String MT_ID = ((Meal) mealToAdd).getMT_ID();
		
	    DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		CallableStatement oracleCallableStmt;
		
		
		try {
			oracleCallableStmt = con.prepareCall("{call SP_INS_MEAL(?,?,?,?,?,?)}");
			
			//oracleCallableStmt.setString(1,  ID);
			oracleCallableStmt.setString(1,  description);
			oracleCallableStmt.setString(2,  name);
			oracleCallableStmt.setDouble(3,  price);
			oracleCallableStmt.setString(4,  imagePath);
			oracleCallableStmt.setString(5,  mealOfDay);
			oracleCallableStmt.setString(6,  MT_ID);
			
			System.out.println("About to call stored procedure");
			oracleCallableStmt.execute();
			oracleCallableStmt.close();
			System.out.println("Stored procedure executed and closed");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Meal inserted, hopefully");
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateInDB(Object mealToUpdate) {
		// do null check on input
		String ID = ((Meal) mealToUpdate).getID();
		String description = ((Meal) mealToUpdate).getDescription();
		String name = ((Meal) mealToUpdate).getName();
		Double price = ((Meal) mealToUpdate).getPrice();
		String imagePath = ((Meal) mealToUpdate).getImagePath();
	    String mealOfDay = ((Meal) mealToUpdate).getMealOfDay();
	    String MT_ID = ((Meal) mealToUpdate).getMT_ID();
		
	    DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		PreparedStatement oraclePreparedStmt;
		try {
			oraclePreparedStmt = con.prepareStatement("update" + " meals set description=?, name=?, price=?, image_path=?, meal_of_day=?, mt_id=? WHERE M_ID=?");
			
			
			oraclePreparedStmt.setString(1,  description);
			oraclePreparedStmt.setString(2,  name);
			oraclePreparedStmt.setDouble(3,  price);
			oraclePreparedStmt.setString(4,  imagePath);
			oraclePreparedStmt.setString(5,  mealOfDay);
			oraclePreparedStmt.setString(6,  MT_ID);
			oraclePreparedStmt.setString(7,  ID);
			oraclePreparedStmt.execute(); // could change to executeUpdate?
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//PreparedStatement oraclePreparedStmt = con.prepareStatement("select " + "first_name from customers where department_id=?");

		
		// Step 4: Execute a Query and Get Results
		//ResultSet oracleRs = oracleStmt.executeQuery("Select first_name, last_name, salary from Employees");
		
		//ResultSet oracleRs = oraclePreparedStmt.getResultSet();
		
		//System.out.println(oracleRs);
		//System.out.println(oracleRs.getFetchSize());
		
		System.out.println("Meal updated, hopefully");
		
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFromDB(Object mealToDelete) { // what if delete when there are depending entries in other tables
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		System.out.println("Getting here");
		
		System.out.println(((Meal) mealToDelete).getID());
		try {
			Statement oracleStmt = con.createStatement();
			
			//con.setAutoCommit(false);

			System.out.println("About to delete");
			PreparedStatement oraclePreparedStmt = con.prepareStatement("Delete from Meals where M_ID=?");
			oraclePreparedStmt.setString(1, ((Meal) mealToDelete).getID());
			
			
			oraclePreparedStmt.executeUpdate();
			//oracleStmt.executeUpdate("DELETE FROM CUSTOMERS WHERE C_ID =" + customerToDelete.getID());
			
			
			//con.commit();
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
		// check size of oracleRs return and mention if empty
		
		System.out.println("Query successful");
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayRecord(Object mealToView) {
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		System.out.println("Getting here");
		
		try {
			Statement oracleStmt = con.createStatement();

			// change to prepared
			ResultSet oracleRs = oracleStmt.executeQuery("Select * from Customers where C_ID =" + ((Meal) mealToView).getID());
			
			int num_fields = 0;
			ResultSetMetaData meta_data;
			
			if (oracleRs != null) {
				meta_data = oracleRs.getMetaData();
				num_fields = meta_data.getColumnCount();
				
				String[] col_names = new String[num_fields];

				
				for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
				    col_names[i - 1] = meta_data.getColumnName(i);
				}	
				
				for (String j:col_names){
					System.out.print("| " + j + " ");
				}
				
			}
			
			
			while (oracleRs.next()) {

				String[] customer_fields = new String[num_fields];
				//String[] col_names = new String[num_fields];
				
				for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
					customer_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
				    //col_names[i - 1] = meta_data.getColumnName(i);
				}		
				
				//System.out.println(meal_fields);
				System.out.println();
				
				for (String j:customer_fields){
					System.out.print("| " + j + " ");
				}
				
			
			}
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
		System.out.println("Query successful");
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// modify to print fields only if a record is found?
	public void displayRecords() {
		
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		
		
		try {
			Statement oracleStmt = con.createStatement();

			
			ResultSet oracleRs = oracleStmt.executeQuery("Select * from Meals");
			
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
