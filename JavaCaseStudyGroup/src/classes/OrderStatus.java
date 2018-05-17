package classes;

public class OrderStatus {
	private String OS_ID;
	private String name;
	/**
	 * @return the oS_ID
	 */
	public String getOS_ID() {
		return OS_ID;
	}
	/**
	 * @param oS_ID the oS_ID to set
	 */
	public void setOS_ID(String oS_ID) {
		OS_ID = oS_ID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order_Status [OS_ID=" + OS_ID + ", name=" + name + "]";
	}
	/**
	 * @param oS_ID
	 * @param name
	 */
	public OrderStatus(String oS_ID, String name) {
		super();
		OS_ID = oS_ID;
		this.name = name;
	}
	
	
}

