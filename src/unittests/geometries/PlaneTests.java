/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * 
 * Unit tests for geometries.Plane class
 * 
 * @author david and matan
 *
 */
public class PlaneTests {

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {

		// =============== Boundary Values Tests ==================

		// Test for 2 similar points
		assertThrows("Constructed a Plane with same points not valid",IllegalArgumentException.class, 
				()->new Plane(new Point3D(1, 2, 3), new Point3D(1, 2, 3), new Point3D(0, 0, 0)));

		// Check for 3 points on one straight line
		assertThrows("Constructed a Plane with points on one straight line is not valid",IllegalArgumentException.class, 
				()->new Plane(new Point3D(1, 1, 1), new Point3D(2, 2, 2), new Point3D(3, 3, 3)));
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// ============ Equivalence Partitions Tests ==============
		// There is a simple single test here
		var pla = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		assertTrue("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3).equals(pla.getNormal(new Point3D(0, 0, 1)))
				|| new Vector(-sqrt3, -sqrt3, -sqrt3).equals(pla.getNormal(new Point3D(0, 0, 1))));
	}

}
