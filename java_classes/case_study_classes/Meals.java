package case_study_classes;
import java.sql.Date;

public class Meals 
{
	String name;
	String description;
	double price;
	Date meal_of_the_day;
	String image_path;
	
	
	public Meals()
	{
		super();
	}
	
	public Meals(String name, String description, double price, Date meal_of_the_day, String image_path) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.meal_of_the_day = meal_of_the_day;
		this.image_path = image_path;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getMeal_of_the_day() {
		return meal_of_the_day;
	}
	public void setMeal_of_the_day(Date meal_of_the_day) {
		this.meal_of_the_day = meal_of_the_day;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	@Override
	public String toString() {
		return "Meals [name=" + name + ", description=" + description + ", price=" + price + ", meal_of_the_day="
				+ meal_of_the_day + ", image_path=" + image_path + "]";
	}
	
	
	
	
	
}
