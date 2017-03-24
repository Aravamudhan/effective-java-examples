// Creates lots of unnecessary duplicate objects - page 20-21
package org.effectivejava.examples.chapter02.item05.slowversion;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*This item should not be misconstrued to imply that object creation is expensive 
and should be avoided.On the contrary,the creation and reclamation of small objects 
whose constructors do little explicit work is cheap,especially on modern JVM implementations.
Creating additional objects to enhance the clarity,simplicity,or power of a program is generally a good thing.
Conversely, avoiding object creation by maintaining your own object pool is a bad idea 
unless the objects in the pool are extremely heavyweight. Example, database connection pool.*/

public class Person {
	private final Date birthDate;

	public Person(Date birthDate) {
		// Defensive copy - see Item 39
		this.birthDate = new Date(birthDate.getTime());
	}

	// DON'T DO THIS!
	public boolean isBabyBoomer() {
		// Unnecessary allocation of expensive object.
		// These instantiations happen every time this method is called. These
		// are not necessary to be called every time, the details need not be
		// modified after initialization.
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
		Date boomStart = gmtCal.getTime();
		gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
		Date boomEnd = gmtCal.getTime();
		return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
	}
}
