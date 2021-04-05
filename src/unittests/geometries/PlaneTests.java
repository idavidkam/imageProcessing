/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;
import org.junit.Test;
import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
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
		assertThrows("Constructed a Plane with same points not valid", IllegalArgumentException.class,
				() -> new Plane(new Point3D(1, 2, 3), new Point3D(1, 2, 3), new Point3D(0, 0, 0)));

		// Check for 3 points on one straight line
		assertThrows("Constructed a Plane with points on one straight line is not valid",
				IllegalArgumentException.class,
				() -> new Plane(new Point3D(1, 1, 1), new Point3D(2, 2, 2), new Point3D(3, 3, 3)));
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

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersectionsRay() {
		// ============ Equivalence Partitions Tests ==============
		
		var plane = new Plane(new Point3D(1, 0, 0), new Vector(new Point3D(0, 0, 1)));
		// TC01: Ray intersects the plane
		assertEquals("Ray should intersects the plane", Point3D.ZERO,
				plane.findIntersections(new Ray(new Point3D(-1, -1, -1), new Vector(1, 1, 1))).get(0));
		// TC02: Ray does not intersect the plane
		assertNull("Ray not should intersects the plane",
				plane.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))));
		
		// =============== Boundary Values Tests ==================

		// TC03: Ray is parallel to the plane (not included in the plane)
		assertNull("Ray is not parallel to the plane",
				plane.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 0, 0))));
		// TC04: Ray is parallel to the plane (included in the plane)
		assertNull("Ray is not parallel to the plane",
				plane.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));
		// TC05: Ray is orthogonal to the plane (P0 before the plane)
		assertEquals("Ray not cross in orthogonal case (P0 before the plane)", new Point3D(1, 1, 0),
				plane.findIntersections(new Ray(new Point3D(1, 1, -1), new Vector(0, 0, 1))).get(0));
		// TC06: Ray is orthogonal to the plane (P0 in the plane)
		assertNull("Ray cross in orthogonal case (P0 in the plane)",
				plane.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 0, 1))));
		// TC07: Ray is orthogonal to the plane (P0 after the plane)
		assertNull("Ray cross in orthogonal case (P0 after the plane)",
				plane.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 0, 1))));
		// TC08: Ray is neither orthogonal nor parallel to and begins at the plane
		// (P0 is in the plane , but not the ray)
		assertNull("Ray not should intersects the plane",
				plane.findIntersections(new Ray(Point3D.ZERO, new Vector(1, 1, 1))));
		// TC09: Ray is neither orthogonal nor parallel to the plane and begins in
		// the same point which appears as reference point in the plane Q
		assertNull("Ray not should intersects the plane",
				plane.findIntersections(new Ray(plane.getQ0(), new Vector(1, 1, 1))));
	}
}