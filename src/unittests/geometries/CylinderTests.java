/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;
import geometries.*;
import primitives.*;

/**
 * Tests for cylinder
 * 
 * @author david and matan
 *
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		var cylinder = new Cylinder(new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 1)), 1, 9);

		// TC01: Check that the normal is correct on body
		assertEquals("getNormal(Point3D) -The normal to the Tube is not correct ", new Vector(new Point3D(1, 0, 0)),
				cylinder.getNormal(new Point3D(1, 0, 6)));

		// TC02:Check that the normal is correct on top cup
		assertEquals("getNormal(Point3D) -The normal to the Tube is not correct ", new Vector(new Point3D(0, 0, 1)),
				cylinder.getNormal(new Point3D(0.5, 0.5, 10)));

		// TC03:Check that the normal is correct on under cup
		assertEquals("getNormal(Point3D) -The normal to the Tube is not correct ", new Vector(new Point3D(0, 0, 1)),
				cylinder.getNormal(new Point3D(0.5, 0.5, 1)));

		// =============== Boundary Values Tests ==================

		// TC04: Check when the point is in center of top cup
		assertEquals("getNormal() faild - point is in center of top cup!", new Vector(new Point3D(0, 0, 1)),
				cylinder.getNormal(new Point3D(0, 0, 10)));

		// TC05: Check when the point is in center of under cup
		assertEquals("getNormal() faild - point is in center of under cup!", new Vector(new Point3D(0, 0, 1)),
				cylinder.getNormal(new Point3D(0, 0, 1)));
	}

}
