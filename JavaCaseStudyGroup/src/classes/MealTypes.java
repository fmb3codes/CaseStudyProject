package classes;

import exceptions.MealTypeExceptions;

public class MealTypes {
	
	private String  MT_ID;
	private String name;
	
	public MealTypes()
	{}
	
	/**
	 * @param mT_ID
	 * @param name
	 */
	public MealTypes(String mT_ID, String name) {
		super();
		MT_ID = mT_ID;
		this.name = name;
	}
	/**
	 * @return the mT_ID
	 */
	public String getMT_ID() {
		return MT_ID;
	}
	/**
	 * @param mT_ID the mT_ID to set
	 */
	public void setMT_ID(String mT_ID) throws MealTypeExceptions {
		
		if(MT_ID.length() < 7)
			throw new MealTypeExceptions("Meal ID must be at least 7 characters");
		else
			this.MT_ID = mT_ID;
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
		return "MealTypes [MT_ID=" + MT_ID + ", name=" + name + "]";
	}
	

}
