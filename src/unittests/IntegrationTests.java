/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;
import org.junit.Test;

import elements.Camera;

/**
 * @author David
 *
 */
public class IntegrationTests {

	/**
	 * test integration ray from camera with sphere
	 */
	@Test
	public void testIntegrationWithSphere() {
		Sphere sphere = new Sphere(new Point3D(0, 0, -3), 1);
		Camera camera = new Camera(Point3D.ZERO, new Vector(0,1,0), new Vector(0,0,-1)); 
		camera.setViewPlaneDistance(1).setViewPlaneSize(3, 3).constructRayThroughPixel(3, 3, 0, 0);
	}

	/**
	 * test integration ray from camera with plane
	 */
	@Test
	public void testIntegrationWithPlane() {
		fail("Not yet implemented");
	}

	/**
	 * test integration ray from camera with triangle
	 */
	@Test
	public void testIntegrationWithTriangle() {
		fail("Not yet implemented");
	}

}
