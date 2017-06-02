// Broken - Inappropriate use of inheritance!
package org.effectivejava.examples.chapter04.item16;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * Depending upon inheritance some times breaks the code, since the
 * implementation might be different than expected or might change at some time.
 * This makes the subclass fragile. Do not inherit a foreign class that is not
 * designed and documented for inheritance.<br/>
 * 
 * @author aravamudhan
 *
 * @param <E>
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
	// The number of attempted element insertions
	private int addCount = 0;

	public InstrumentedHashSet() {
	}

	public InstrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}

	public static void main(String[] args) {
		InstrumentedHashSet<String> s = new InstrumentedHashSet<String>();
		s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
		// Expected 3 but returns 6
		System.out.println(s.getAddCount());
	}
}
