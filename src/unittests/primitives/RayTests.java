/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author David
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(primitives.Point3D[])}.
	 */
	@Test
	public void testFindClosestPoint() {
		
		Ray ray = new Ray(Point3D.ZERO,new Vector(1,0,0));
		// ============ Equivalence Partitions Tests ==============
		
		//TC01: A point in the middle of the list is closest to the beginning of the Ray
		assertEquals("The middle point in the list is closest", new Point3D(1.5, 0, 0),
				ray.findClosestPoint(List.of(new Point3D(2,0,0), new Point3D(1.5, 0, 0), new Point3D(2, -1, 0))));
		
		// =============== Boundary Values Tests ==================
		
		//TC02: empty list
		assertNull("empty list: expect to return null", ray.findClosestPoint(List.of()));
		
		//TC03: The first point is closest to the beginning of the Ray
		assertEquals("The first point is closest", new Point3D(2, 0, 0),
				ray.findClosestPoint(List.of(new Point3D(2,0,0), new Point3D(2, 1, 0), new Point3D(2, -1, 0))));
		
		//TC04: The last point is closest to the beginning of the Ray
		assertEquals("The last point is closest", new Point3D(2, 0, 0),
				ray.findClosestPoint(List.of(new Point3D(2,-1,0), new Point3D(2, 1, 0), new Point3D(2, 0, 0))));
	}

}
