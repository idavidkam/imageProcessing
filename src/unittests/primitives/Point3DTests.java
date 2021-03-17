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
		// ============ Equivalence Partitions Tests ==============

		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {
		// ============ Equivalence Partitions Tests ==============

		fail("Not yet implemented");
	}

}
