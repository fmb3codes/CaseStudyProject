package interfaces;

public interface ServiceOperations {
	// Only definitions
	public void insertToDB(Object o); // methods defined within interface will be considered abstract (method will not have its own implementation, only definition)
	public void updateInDB(Object o);
	public void deleteFromDB(Object o);
	public void displayRecord(Object o);
	public void displayRecords();
}
