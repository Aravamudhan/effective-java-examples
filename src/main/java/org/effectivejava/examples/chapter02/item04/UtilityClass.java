// Noninstantiable utility class
package org.effectivejava.examples.chapter02.item04;

public class UtilityClass {
	// Suppress default constructor for noninstantiability
	// Making the default constructor prevents object instantiation
	private UtilityClass() {
		// If created inside the same class, the error is thrown
		throw new AssertionError("Can not create object using constructor");
	}

	public static void main(String... args) {
		// AssertionError
		new UtilityClass();
	}
}
