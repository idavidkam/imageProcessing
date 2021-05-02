package unittests.geometries;

import static org.junit.Assert.*;
import org.junit.Test;
import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Geometries
 * 
 * @author David and Matan
 *
 */
public class GeometriesTests {

	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {

		// ============ Equivalence Partitions Tests ==============
		var geometries = new Geometries(new Plane(new Point3D(-4, 0, 0), new Point3D(0, 0, 2), new Point3D(0, 0, 0)),
				new Sphere(new Point3D(0, 3, 0), 2),
				new Triangle(new Point3D(0, -2, 4), new Point3D(0, -2, 0), new Point3D(4, -2, 0)));

		// TC01: Some (but not all) shapes are cut
		assertEquals("Only one shape is cut", 3,
				geometries.findIntersections(new Ray(new Point3D(0.5, -1, 0.5), new Vector(0, 1, 0))).size());

		// =============== Boundary Values Tests ==================

		Geometries geometriesEmpty = new Geometries();

		// TC02: Empty body collection
		assertNull("is Empty!",
				geometriesEmpty.findIntersections(new Ray(new Point3D(2, 4, -3), new Vector(-1, 0, 1))));

		// TC03: No shape is cut
		assertNull("without crossing!",
				geometries.findIntersections(new Ray(new Point3D(-1, 0.5, 0), new Vector(1, 0, 0))));

		// TC04: Only one shape is cut
		assertEquals("Only one shape is cut", 2,
				geometries.findIntersections(new Ray(new Point3D(0, 0.5, 0), new Vector(0, 1, 0))).size());

		// TC05: All shapes are cut
		assertEquals("Only one shape is cut", 4,
				geometries.findIntersections(new Ray(new Point3D(0.5, 6, 0.5), new Vector(0, -1, 0))).size());
	}
}