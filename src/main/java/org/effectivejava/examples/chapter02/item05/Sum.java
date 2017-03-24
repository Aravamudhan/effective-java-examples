package org.effectivejava.examples.chapter02.item05;

// Always try to reuse objects wherever possible. Do not create unnecessary object. 
// Use a static factory method to get immutable instances.
public class Sum {
	// Hideously slow program!
	public static void main(String[] args) {
		// Wrapper objects are implemented to be immutable. Hence operations on
		// them create new objects. Always prefer primitives to boxed primitive
		// types.
		Long sum = 0L;
		Long copy = sum;
		System.out.println("Are copy and sum same ? " + (copy == sum));
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			// New object every time. The sum reference points to new object
			// every time i is added to it
			sum += i;
		}
		System.out.println(sum);
		// Will be false
		System.out.println("Are copy and sum same ? " + (copy == sum));
	}
}
