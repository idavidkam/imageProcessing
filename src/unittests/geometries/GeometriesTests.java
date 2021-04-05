/**
 * 
 */
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
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		fail("Not yet implemented");
		
		// ============ Equivalence Partitions Tests ==============
		
		// =============== Boundary Values Tests ==================
		Geometries geometroies = new Geometries();
		//TC01: Empty body collection
		assertNull("is Empty!",
				geometroies.findIntersections(new Ray(new Point3D(2, 4,-3), new Vector(-1, 0, 1))));
		//TC02: No shape is cut
		geometroies.add(new Plane(new Point3D(-4, 0, 0),new Point3D(0, 0, 2),new Point3D(0, 0, 0)), 
				new Sphere(new Point3D(0, 3, 0), 2), 
				new Triangle(new Point3D(0, -3, 0), new Point3D(0, -2, 0), new Point3D(1, -2, 0)));
		assertNull("without crossing!", 
				geometroies.findIntersections(new Ray(new Point3D(-1,0.5,0), new Vector(1, 0, 0))));
		//TC03:
		//TC04:
		//TC05:
		

	}

}
