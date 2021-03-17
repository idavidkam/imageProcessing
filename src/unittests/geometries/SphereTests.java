/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;


import org.junit.Test;

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
		var ball=new Sphere(new Point3D(0, 0, 0), 1);
		
		//Check that the normal is correct
		assertEquals("getNormal(Point3D) -The normal to the Sphere is not correct ", new Vector(1, 0, 0),ball.getNormal(new Point3D(1, 0, 0)));
	}

}
