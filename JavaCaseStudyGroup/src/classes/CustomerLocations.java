package classes;

public class CustomerLocations {
	
	CustomerLocations cusLoc;
	
	
	private String C_ID;
	private String LT_ID;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	/**
	 * @return the cusLoc
	 */
	public CustomerLocations getCusLoc() {
		return cusLoc;
	}
	/**
	 * @param cusLoc the cusLoc to set
	 */
	public void setCusLoc(CustomerLocations cusLoc) {
		this.cusLoc = cusLoc;
	}
	/**
	 * @return the c_ID
	 */
	public String getC_ID() {
		return C_ID;
	}
	/**
	 * @param string the c_ID to set
	 */
	public void setC_ID(String string) {
		C_ID = string;
	}
	/**
	 * @return the lT_ID
	 */
	public String getLT_ID() {
		return LT_ID;
	}
	/**
	 * @param lT_ID the lT_ID to set
	 */
	public void setLT_ID(String lT_ID) {
		LT_ID = lT_ID;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @param cusLoc
	 * @param c_ID
	 * @param lT_ID
	 * @param street
	 * @param city
	 * @param state
	 * @param country
	 * @param zipcode
	 */
	public CustomerLocations(CustomerLocations cusLoc, String c_ID, String lT_ID, String street, String city,
			String state, String country, String zipcode) {
		super();
		this.cusLoc = cusLoc;
		C_ID = c_ID;
		LT_ID = lT_ID;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
	}
	public CustomerLocations() {
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerLocations [cusLoc=" + cusLoc + ", C_ID=" + C_ID + ", LT_ID=" + LT_ID + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", zipcode=" + zipcode + "]";
	}
	

}
