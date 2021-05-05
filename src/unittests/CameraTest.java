package unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import elements.Camera;
import primitives.*;

/**
 * Testing Camera Class
 * 
 * @author david and matan
 *
 */
public class CameraTest {

	/**
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}.
	 */
	@Test
	public void testConstructRayThroughPixel() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)).setViewPlaneDistance(10);

		// ============ Equivalence Partitions Tests ==============
		// TC01: 3X3 Corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 0, 0));

		// TC02: 4X4 Corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-3, -3, 10)),
				camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 0, 0));

		// TC03: 4X4 Side (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -3, 10)),
				camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 1, 0));

		// TC04: 4X4 Inside (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -1, 10)),
				camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 1, 1));

		// =============== Boundary Values Tests ==================
		// TC11: 3X3 Center (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, 0, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 1, 1));

		// TC12: 3X3 Center of Upper Side (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -2, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 1, 0));

		// TC13: 3X3 Center of Left Side (1,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, 0, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 0, 1));
	}

	/**
	 * Test method for {@link elements.Camera#rotationTransformation(double, int)}.
	 */
	@Test
	public void testRotationTransformation() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));

		// TC01: Rotate camera on up or down
		assertEquals("rotationTransformation() the vector not correct", new Vector(0, 0, 1),
				camera.rotationTransformation(Math.PI / 2.0, 0).getvTo());
		// TC02: Rotate camera on left or right
		assertEquals("rotationTransformation() the vector not correct", new Vector(0, 1, 0),
				camera.rotationTransformation(Math.PI / 2.0, 1).getvTo());

		// TC03: Rotate camera on place
		assertEquals("rotationTransformation() the vector not correct", new Vector(1, 0, 0),
				camera.rotationTransformation(Math.PI / 2.0, 2).getvRight());
	}

	/**
	 * Test method for {@link elements.Camera#TranslationTransformation(Point3D)}
	 */
	@Test
	public void testTranslationTransformation() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		// TC01: Translation of camera
		assertEquals("translationTransformation() position not correct", new Point3D(20, 0, 20),
				camera.translationTransformation(new Vector(20, 0, 20)).getP0());
	}
}