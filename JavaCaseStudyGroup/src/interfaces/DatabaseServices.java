package interfaces;

import java.sql.SQLException;

public interface DatabaseServices 
{
	public void Create() throws SQLException, Exception;
	public void Update() throws SQLException, Exception;
	public void Delete() throws SQLException, Exception;
	public void GetAll() throws SQLException, Exception;
	public void Find() throws SQLException, Exception;
	
}
