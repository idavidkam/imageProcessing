/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
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
		try {
			var pl = new Plane(new Point3D(1, 2, 3), new Point3D(1, 2, 3), new Point3D(0, 0, 0));
			fail("Constructed a Plane with same points not valid");
		} catch (Exception e) {
		}

		// Check for 3 points on one straight line
		try {
			var pl = new Plane(new Point3D(1, 1, 1), new Point3D(2, 2, 2), new Point3D(3, 3, 3));
			fail("Constructed a Plane with points on one straight line is not valid");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// ============ Equivalence Partitions Tests ==============
		var pl = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 0));
        
		//Test that the length of the normal vector is equal to 1
		assertTrue("getNormal(Point3D) faild - the vector no normalizes", pl.getNormal(new Point3D(1, 0, 0)).length() == 1);
		
		//Test that vectors are orthogonal
		assertTrue("getNormal(Point3D) faild - the vector not orthogonal",Util.isZero(pl.getNormal(new Point3D(1, 0, 0)).dotProduct(new Vector(1, 0, 0))));
	}

}
