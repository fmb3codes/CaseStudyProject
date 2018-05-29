package interfaces;

import java.sql.SQLException;

public interface DatabaseServices 
{
	public void Create() throws SQLException, Exception;
	public void Update() throws SQLException, Exception;
	public void Delete() throws SQLException, Exception;
	public void GetAll() throws SQLException, Exception;
	public void Find() throws SQLException, Exception;
	
	//Used to increment char[7] id's used in database
	public static String incrementID (String id){
		return String.format("%07d", Integer.parseInt(id)+1);
	}
	
}
