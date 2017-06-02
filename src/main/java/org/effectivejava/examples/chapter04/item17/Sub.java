package org.effectivejava.examples.chapter04.item17;

import java.util.Date;

public final class Sub extends Super {
	private final Date date; // Blank final, set by constructor

	Sub() {
		System.out.println("Sub class constr");
		date = new Date();
	}

	// Overriding method invoked by superclass constructor before invoking the
	// subclass constructor. This results in date not being initialized
	@Override
	public void overrideMe() {
		System.out.println("Date: " + date);
	}

	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.overrideMe();
		Super sup = new Sub();
		sup.overrideMe();
	}
}
