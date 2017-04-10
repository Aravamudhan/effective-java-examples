// Broken - violates symmetry! - Pages 36-37
package org.effectivejava.examples.chapter03.item08;

import java.util.ArrayList;
import java.util.List;

/**
 * Once equals contract is violated the behavior of objects will become
 * inconsistent.
 * 
 * @author aravamudhan
 *
 */
public final class CaseInsensitiveString {
	private final String s;

	public CaseInsensitiveString(String s) {
		if (s == null)
			throw new NullPointerException();
		this.s = s;
	}

	// Broken - violates symmetry!
	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseInsensitiveString)
			return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
		// When comparing a CaseInsensitiveString object with a String,
		// the statements below will be executed. But when comparing String with
		// CIS, equals will fail.
		if (o instanceof String)
			return s.equalsIgnoreCase((String) o);
		return false;
	}

	// This version is correct. Because we are comparing the attribute 's' of
	// two CaseInsensitiveString objects here
	// @Override public boolean equals(Object o) {
	// return o instanceof CaseInsensitiveString &&
	// ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
	// }

	public static void main(String[] args) {
		CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
		String s = "polish";
		System.out.println(cis.equals(s) + "  " + s.equals(cis));
		List<CaseInsensitiveString> cisList = new ArrayList<>();
		cisList.add(cis);
		// This returns false
		System.out.println(cisList.contains(s));
	}
}
