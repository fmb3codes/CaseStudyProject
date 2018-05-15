package classes;
//import oracle.jdbc.*;
import java.sql.*;


	public class CustomerLocationServices {
		private CustomersDBconnection cus_connection;
		
			
			public CustomerLocationServices(CustomersDBconnection db){
					
					this.cus_connection= db;
			}
			
			public void newLocation(CustomerLocations location) throws Exception
			{
					PreparedStatement oraclePreparedStmt = cus_connection.getConnection().prepareStatement("INSERT INTO Customers_locations"
                            + "(CD_ID, LT_ID, Street_address, city, state, zip_code, c_id) VALUES"
                            + "(?,?,?,?,?,?,?)");
					 oraclePreparedStmt.setInt(1,1);
					 oraclePreparedStmt.setInt(2, 1);
					 oraclePreparedStmt.setString(3, "");
					 oraclePreparedStmt.setString(4,"");
					 oraclePreparedStmt.setString(5, ""); 
					 oraclePreparedStmt.setInt(6,1);
					 oraclePreparedStmt.setInt(7, 1);
			            
			            
					 oraclePreparedStmt.executeUpdate();
			
			}
			
					 public void deleteLocation(CustomerLocations location) throws Exception
						{
							PreparedStatement oraPreparedStmt = 
									cus_connection.getConnection().prepareStatement(""
											+ "DELETE FROM Customers Locations WHERE Customer Location ID# = ?");
							oraPreparedStmt.setString(1, location.getC_ID());
							oraPreparedStmt.executeUpdate();
							System.out.println("Location " + location.getC_ID() + " has been succesfully deleted");
						}
						
	           
					 public void getAllOrders() throws Exception
						{
							ResultSet oraResult = cus_connection.getStatement().executeQuery("SELECT * FROM ORDERS");
							String format = "|%1$-3s|%2$-10s|%3$-10s|%4$-5s|%5$-3s|%6$-3s|%7$-3s|\n";
							while(oraResult.next())
							{
								System.out.format(
										format,
										oraResult.getString("C_ID"), 
										oraResult.getString("LT_ID"), 
										oraResult.getString("Street"),
										oraResult.getString("city"),
										oraResult.getString("state"),
										oraResult.getString("country"),
										oraResult.getString("zip code")
								);
							}
						}
						public void getSpecificOrder(CustomerLocations locations) throws Exception
						{
							PreparedStatement oraPrepStmt = cus_connection.getConnection().prepareStatement(
									"SELECT * FROM ORDERS WHERE O_ID = ?");
							oraPrepStmt.setString(1, locations.getC_ID());
							ResultSet result = oraPrepStmt.executeQuery();
							String format = "|%1$-3s|%2$-10s|%3$-10s|%4$-5s|%5$-3s|%6$-3s|%7$-3s|%8$-3s|\n";
							while(result.next())
							{
								System.out.format(
										format,
										result.getString("O_ID"), 
										result.getString("ORDER_DATE"), 
										result.getString("DELIVERY_DATE"),
										result.getString("ORDER_ON_HOLD"),
										result.getString("TIMES_CHANGED"),
										result.getString("C_ID"),
										result.getString("OS_ID"),
										result.getString("CL_ID")
								);
							}
	           
			
						}
						
						
						
	}
	

	

