// Attempting to add a value component to Point - Pages 37 - 38
package org.effectivejava.examples.chapter03.item08;

public class ColorPoint extends Point {
	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	// Broken - violates symmetry!
	// If a ColorPoint is compared with Point this equals method is called and
	// it would return false. But on the other hand, if Point is compared with
	// ColorPoint, equals method of Point is called. That would ignore the color
	// attribute and will compare only x and y.It might return true too if x and
	// y are same ignoring the color attribute. Route of this problem is
	// extending a root class and adding a component. To this new subclass.
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ColorPoint))
			return false;
		return super.equals(o) && ((ColorPoint) o).color == color;
	}

	// Broken - violates transitivity!
	// @Override public boolean equals(Object o) {
	// if (!(o instanceof Point))
	// return false;
	//
	// // If o is a normal Point, do a color-blind comparison
	// if (!(o instanceof ColorPoint))
	// return o.equals(this);
	//
	// // o is a ColorPoint; do a full comparison
	// return super.equals(o) && ((ColorPoint)o).color == color;
	// }

	public static void main(String[] args) {
		// First equals function violates symmetry
		Point p = new Point(1, 2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);
		// Returns true and then false
		System.out.println(p.equals(cp) + " " + cp.equals(p));

		// Second equals function violates transitivity
		// Both the ColorPoints are different. But when compared with Point,
		// both would return true. When compared with each other they will
		// return false. This violates transitivity. a.equals(b) and b.equals(c)
		// but a.equals(c) fails
		ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		Point p2 = new Point(1, 2);
		ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
		System.out.printf("%s %s %s%n", p1.equals(p2), p2.equals(p3), p1.equals(p3));
	}
}
