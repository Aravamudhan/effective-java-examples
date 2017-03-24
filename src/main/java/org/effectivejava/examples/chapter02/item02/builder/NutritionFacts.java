// Builder Pattern - Pages 14-15
package org.effectivejava.examples.chapter02.item02.builder;

public class NutritionFacts {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	public static class Builder {
		// Required parameters
		private final int servingSize;
		private final int servings;

		// Optional parameters - initialized to default values
		private int calories = 0;
		private int fat = 0;
		private int carbohydrate = 0;
		private int sodium = 0;

		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public Builder calories(int val) {
			calories = val;
			return this;
		}

		public Builder fat(int val) {
			fat = val;
			return this;
		}

		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		public Builder sodium(int val) {
			sodium = val;
			return this;
		}

		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}

	private NutritionFacts(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}

	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
		Student s = new Student.Builder("James", 25).build();
		System.out.println(s);
		// Required parameters in the Builder constructor and optional parameters in a separate setter like methods
		Student t = new Student.Builder("John", 30).gender("Male").build();
		System.out.println(t);
	}
}

/**
 * Have a Builder class<br/>
 * Replicate the attributes in the Builder too<br/> 
 * Call the Builder constructor and necessary methods(only gender() here).<br/>
 * Finally call the build method of the Builder<br/>
 * Builder is appropriate when there are lot of parameters. Better suited, if they are optional.<br/>
 * One disadvantage is the builder must be created before the object. Also it is more verbose<br/>
 * @author aravamudhan
 *
 */
class Student {
	private final String name;
	private final int age;
	private final String gender;

	private Student(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.gender = builder.gender;
	}

	static class Builder {
		// Required parameters
		private final String name;
		private final int age;
		// Optional parameter
		private String gender = "Not specified";

		// Set the required parameter here in the constructor of the Builder
		Builder(String name, int age) {
			this.name = name;
			this.age = age;
		}

		// Set the optional parameters in a separate method
		Builder gender(String gender) {
			this.gender = gender;
			return this;
		}

		// After a student object has been built this is the final method to
		// call. This calls the constructor of the Student and sets all the
		// values
		Student build() {
			return new Student(this);
		}
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}

}