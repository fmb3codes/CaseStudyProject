package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection 
{
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
<<<<<<< HEAD
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "db_u26";
=======
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "db_u28";
>>>>>>> d55fcdfbede13303301e419d260f5108d4cd5caf
	private static final String DB_PASSWORD = "pass";
	private Connection con;
	private Statement oraStmt;
	
	public Connection getConnection() {return con;}
	public Statement getStatement() {return oraStmt;}
	
	public DatabaseConnection()
	{
		try
		{
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			oraStmt = con.createStatement();
			System.out.println("Connection Succesful");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
