package services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Admin;
import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class AdminServices implements DatabaseServices
{
	private DatabaseConnection db_connection;
	private Admin admin;
	
	public AdminServices()
	{
		
	}
	
	public AdminServices(DatabaseConnection db)
	{
		this.db_connection = db;
	}
	
	public AdminServices(DatabaseConnection db, Admin admin)
	{
		this.db_connection = db;
		this.admin = admin;
	}

	@Override
	public void Create() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Find() throws SQLException, Exception 
	{
		
	}
	
	public Admin AdminExists() throws SQLException, Exception 
	{
		PreparedStatement oraPrepStmt = db_connection
				.getConnection()
				.prepareStatement("SELECT * FROM ADMINS WHERE A_ID = ? AND PASSWORD = ?");
		oraPrepStmt.setString(1, admin.getAdminID());
		oraPrepStmt.setString(2, admin.getPassword());
		ResultSet result = oraPrepStmt.executeQuery();
		
		while(result.next())
		{
			this.admin.setAdminID(result.getString("A_ID"));
			this.admin.setFirstName(result.getString("FIRST_NAME"));
			this.admin.setLastName(result.getString("LAST_NAME"));
			this.admin.setPassword(result.getString("PASSWORD"));
		}
	
		
		return this.admin;
	}

}
