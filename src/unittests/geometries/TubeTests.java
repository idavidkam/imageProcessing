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
		var tube = new Tube(new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 1)), 1);

		// Check that the normal is correct
		assertEquals("getNormal(Point3D) -The normal to the Tube is not correct ",
				new Vector(new Point3D(1, 0, 0)).normalize(), tube.getNormal(new Point3D(1, 0, 6)));
		
		// =============== Boundary Values Tests ==================
		// Check when the point is in front of the head Ray
		assertEquals("getNormal() faild - point is in front of the head Ray!", new Vector(new Point3D(0, 1, 0)),
				tube.getNormal(new Point3D(0, 1, 1)));
	}
}