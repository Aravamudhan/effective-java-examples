// Can you spot the "memory leak"?
package org.effectivejava.examples.chapter02.item06;

import java.util.Arrays;

public class Stack {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();
		// Memory leak. The object that is being popped is not dereference
		// here. But we are simply reducing the size of the elements array and
		// the popped object is referenced by the array still. To avoid the
		// memory leaks that will be possible by this sort of statements, null
		// out the reference. element[index] = null
		return elements[--size];
	}

	/**
	 * Ensure space for at least one more element, roughly doubling the capacity
	 * each time the array needs to grow.
	 */
	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
}
