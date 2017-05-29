package org.effectivejava.examples.chapter03.item11;

// Better to use a factory method or a copy constructor rather than using clone method.   
public class CloneTest {

	public static void main(String[] args) {
		CloneTestClass c1 = new CloneTestClass();
		c1.a = 100;
		c1.b = 200;
		CloneTestClass c2 = c1.clone();
		System.out.println(c2.a);
		System.out.println(c2.b);
		CloneTestSubClass c3 = new CloneTestSubClass();
		CloneTestSubClass c4 = c3.clone();
		System.out.println(c4.a);
		System.out.println(c4.b);
		System.out.println(c4.c);
		System.out.println(c4.d);
		System.out.println(c4.cat.ca);
		System.out.println(c4.cat.cb);
		System.out.println("Final cat.ca " + c4.finalCat.ca);
		System.out.println("Final cat.cb " + c4.finalCat.cb);
		c4.cat.cb = 500;
		System.out.println(c3.cat.cb);
		System.out.println(c4.cat.cb);
	}

}

// If the cloneable is not implemented, calling the clone method would throw
// CloneNotSupportedException
class CloneTestClass implements Cloneable {
	int a = 10;
	int b = 20;

	@Override
	public CloneTestClass clone() {
		CloneTestClass ct = null;
		try {
			ct = (CloneTestClass) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return ct;
	}
}

class CloneTestSubClass extends CloneTestClass implements Cloneable {
	int c = 30;
	int d = 40;
	// To copy this too deep copy is needed
	CloneAnotherTest cat = new CloneAnotherTest();
	// Cannot clone an immutable object
	final CloneAnotherTest finalCat = new CloneAnotherTest();

	@Override
	public CloneTestSubClass clone() {
		CloneTestSubClass ct = null;
		ct = (CloneTestSubClass) super.clone();
		// With out this method call, same instance of the cat will be used in
		// the cloned object too. This would have been problematic had the cat
		// object been a final reference. Of course, there is no point in
		// providing clone method for a class with final references.
		ct.cat = cat.clone();
		return ct;
	}
}

class CloneAnotherTest implements Cloneable {
	int ca = 60;
	int cb = 70;

	// Without this clone method, and explicitly calling this in the
	// CloneTestSubClass's clone method, any object that is cloned from a
	// CloneTestSubClass object will share the same instance of
	// CloneTestSubClass.cat
	@Override
	public CloneAnotherTest clone() {
		CloneAnotherTest cat = null;
		try {
			cat = (CloneAnotherTest) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cat;
	}
}

// This will throw the CloneNotSupportedException even after overridding the
// clone method with the reason being the non implementation of the Cloneable
// interface
class CloneExeceptionTest {
	int x = 10;

	@Override
	public CloneExeceptionTest clone() {
		CloneExeceptionTest ct = null;
		try {
			ct = (CloneExeceptionTest) super.clone();
		} catch (CloneNotSupportedException e) {

		}
		return ct;
	}
}
