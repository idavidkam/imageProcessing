/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;

/**
 * @author m.n.y.1234
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for
	 * {@link renderer.ImageWriter#ImageWriter(java.lang.String, int, int)}.
	 */
	@Test
	public void testImageWriter() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1))
				.setViewPlaneDistance(1)
				.setViewPlaneSize(500, 800);
	    var writer=new ImageWriter("firstImage",500,800);
	    for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 10; i++) {
				
			}
		}
	}
}
