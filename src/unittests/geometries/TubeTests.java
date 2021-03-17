/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Tube class
 * 
 * @author david and matan
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		var tube = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 1);

		// Check that the normal is correct
		double sqrt10 = Math.sqrt(10);
		assertEquals("getNormal(Point3D) -The normal to the Tube is not correct ",
				new Vector(new Point3D(0, 1 / sqrt10, 3 / sqrt10)), tube.getNormal(new Point3D(0, 1, 3)));
		// =============== Boundary Values Tests ==================
		// Check when the point is in front of the head Ray
		assertThrows("getNormal() faild - point is in front of the head Ray!", IllegalArgumentException.class,
				() -> tube.getNormal(new Point3D(0, 1, 0)));
	}
}
