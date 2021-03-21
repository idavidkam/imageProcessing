/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sun.net.httpserver.Authenticator.Result;

import geometries.Sphere;
import primitives.*;

/**
 * Unit tests for geometries.Sphere class
 * 
 * @author david and matan
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		var ball = new Sphere(new Point3D(0, 0, 0), 1);

		// Check that the normal is correct
		assertEquals("getNormal(Point3D) -The normal to the Sphere is not correct ", new Vector(1, 0, 0),
				ball.getNormal(new Point3D(1, 0, 0)));
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		// ============ Equivalence Partitions Tests ==============
		var sphere = new Sphere(new Point3D(1, 0, 0), 1);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)

		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));

		assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        
        // TC04: Ray starts after the sphere (0 points)
        

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        // TC12: Ray starts at sphere and goes outside (0 points)

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        // TC14: Ray starts at sphere and goes inside (1 points)
        // TC15: Ray starts inside (1 points)
        // TC16: Ray starts at the center (1 points)
        // TC17: Ray starts at sphere and goes outside (0 points)
        // TC18: Ray starts after sphere (0 points)

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        // TC20: Ray starts at the tangent point
        // TC21: Ray starts after the tangent point

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line

    }
}
