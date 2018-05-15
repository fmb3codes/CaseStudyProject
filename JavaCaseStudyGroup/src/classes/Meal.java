package classes;

import java.sql.Date;

import exceptions.caseStudyIDException;

public class Meal {
	String ID;
	String description;	
	String name;
	Double price;
	String imagePath;
	String mealOfDay;
	String MT_ID;
	
	public static void main(String[] args) {
		
		System.out.println("Test");
		Meal testMeal = new Meal("5", "some soup", "soup", 1.2, "img_path", "11-6-1923", "7");
		//Meal testMeal1 = new Meal(2, "some soup", "Soup", 1.2, "img_path", "11-6-1923", 5);

	}
	
	public Meal() {
		// TODO Auto-generated constructor stub
	}


 // create exception class (e.g. ID Exception)
	
	// put throws when you want the caller to handle an exception
	public Meal(String iD, String description, String name, Double price, String imagePath, String mealOfDay, String mT_ID) {
		super();
		//this.ID = iD;
		
		// put in try catch to gracefully exit; auto assign id?
		try {
			//System.out.println(iD.length());
			if (iD.length() < 7 || iD.length() > 7){
				throw new caseStudyIDException("ID must be at least 7 characters");
				
			}
			else {
				this.ID = iD;
			}
		}
		catch (Exception caseStudyIDException) {
			System.out.println(caseStudyIDException.getMessage());
		}

		this.description = description;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
		this.mealOfDay = mealOfDay;
		this.MT_ID = mT_ID;
	}



	public String getID() {
		return ID;
	}



	public void setID(String iD) {
		try {
			//System.out.println(iD.length());
			if (iD.length() < 7 || iD.length() > 7){
				throw new caseStudyIDException("ID must be at least 7 characters");
				
			}
			else {
				this.ID = iD;
			}
		}
		catch (Exception caseStudyIDException) {
			System.out.println(caseStudyIDException.getMessage());
		}
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public String getMealOfDay() {
		return mealOfDay;
	}



	public void setMealOfDay(String mealOfDay) {
		this.mealOfDay = mealOfDay;
	}



	public String getMT_ID() {
		return MT_ID;
	}



	public void setMT_ID(String mT_ID) {
		MT_ID = mT_ID;
	}



	@Override
	public String toString() {
		return "Meal [ID=" + ID + ", description=" + description + ", name=" + name + ", price=" + price
				+ ", imagePath=" + imagePath + ", mealOfDay=" + mealOfDay + ", MT_ID=" + MT_ID + "]";
	}
	
	

}
