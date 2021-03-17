/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * Unit tests for primitives.Point3D class
 * 
 * @author David and Matan
 *
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		var p1 = new Point3D(2, 2, 2);

		// ============ Equivalence Partitions Tests ==============

		// Test that subtract is proper
		assertTrue("subtract() Point - Point does not work correctly",
				new Vector(1, 1, 1).equals(p1.subtract(new Point3D(1, 1, 1))));

	}

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		var p1 = new Point3D(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============

		// Test that add is proper
		assertTrue("add() Point + Vector does not work correctly", Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))));
	}

	/**
	 * Test method for
	 * {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
	 */
	@Test
	public void testDistanceSquared() {
		var p1 = new Point3D(4, 0, 0);
		var p2 = new Point3D(0, 3, 0);

		// ============ Equivalence Partitions Tests ==============
		// Test that Distance Squared is proper
		assertEquals("distanceSquared() the distance squared is not proper", 25, p1.distanceSquared(p2), 0.000001);
	}

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {
		var p1 = new Point3D(1, 2, 3);
		var p2 = new Point3D(1, 2, 3);
		
		// ============ Equivalence Partitions Tests ==============
		// Test that Distance is proper
		assertEquals("distance() the distance squared is not proper", 0, p1.distance(p2), 0.000001);
	}
}
