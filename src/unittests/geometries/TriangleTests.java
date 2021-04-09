/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;
import org.junit.Test;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Triangle class
 * 
 * @author david and matan
 */
public class TriangleTests {

	/**
	 * Test method for
	 * {@link geometries.Triangle#Triangle(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testTriangle() {
		// ============ Equivalence Partitions Tests ==============

		// Test that triangle compliant with geometry Triangle
		assertThrows("triangle constructer() - not compliant with geometry Triangle", IllegalArgumentException.class,
				() -> new Triangle(new Point3D(1, 1, 1), new Point3D(2, 2, 2), new Point3D(3, 3, 3)));
		
		// Test that triangle no with same points
		assertThrows("triangle constructer() - you hava same points", IllegalArgumentException.class,
				() -> new Triangle(new Point3D(3, 4, 0), new Point3D(3, 4, 0), new Point3D(3, 3, 3)));
	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============

		// There is a simple single test here
		var tri = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		assertTrue("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3).equals(tri.getNormal(new Point3D(0, 0, 1)))
				|| new Vector(-sqrt3, -sqrt3, -sqrt3).equals(tri.getNormal(new Point3D(0, 0, 1))));
	}
	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersectionsRay() {
		
		Triangle triangle = new Triangle(new Point3D(0, 0, 3), new Point3D(3, 0, 0), Point3D.ZERO);
		
		// ============ Equivalence Partitions Tests ==============
		
		//TC01: Inside triangle
		assertEquals("the Ray not cross inside triangle ",new Point3D(1,0,1), 
				triangle.findIntersections(new Ray(new Point3D(1, -1, 1), new Vector(0, 1, 0))).get(0));
		
		//TC02: Outside against edge
		assertNull("the Ray not cross outside against edge",
				triangle.findIntersections(new Ray(new Point3D(-1, -1, 1), new Vector(0, 1, 0))));
		
		//TC03: Outside against vertex
		assertNull("the Ray not cross outside against vertex",
				triangle.findIntersections(new Ray(new Point3D(-1, -1, -1), new Vector(0, 1, 0))));
		
		// =============== Boundary Values Tests ==================
		
		//TC04: the ray begins "before" the plane (On edge)
		assertNull("the ray begins before the plane and not cross on edge",
				triangle.findIntersections(new Ray(new Point3D(0, -1, -1), new Vector(0, 1, 0))));
		
		//TC05: the ray begins "before" the plane (In vertex)
		assertNull("the ray begins before the plane and not cross in vertex",
				triangle.findIntersections(new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 0))));
		
		//TC06: the ray begins "before" the plane (On edge's continuation)
		assertNull("the ray begins before the plane and not cross on edge's continuation",
				triangle.findIntersections(new Ray(new Point3D(0, -1, 3.5), new Vector(0, 1, 0))));
	}
	
}