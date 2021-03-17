/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Polygon;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

/**
 * Unit tests for geometries.Triangle class
 * 
 * @author david and matan
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#Triangle(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testTriangle() {
        // ============ Equivalence Partitions Tests ==============

		//Test that triangle compliant with geometry Triangle
		try {
			var tri=new Triangle(new Point3D(3, 4, 0), new Point3D(4, 3, 0), new Point3D(9, 9, 0));
			fail("Constructed a Triangle -not compliant with geometry Triangle");
		} catch (Exception e) {
			// TODO: handle exception
		}
		//Test that triangle no with same points
		try {
			var tri=new Triangle(new Point3D(3, 4, 0), new Point3D(3, 4, 0), new Point3D(9, 9, 0));
			fail("Constructed a Triangle - with same points");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

		//There is a simple single test here
        var tri = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), tri.getNormal(new Point3D(0, 0, 1)));
	}

}
