package org.effectivejava.examples.chapter04.item17;

public class Super {
	// Broken - constructor invokes an overridable method
	public Super() {
		System.out.println("Super class constr");
		overrideMe();
	}

	public void overrideMe() {
		System.out.println("Super class overrideMe");
	}
}
