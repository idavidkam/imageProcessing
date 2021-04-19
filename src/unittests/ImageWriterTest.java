/**
 * 
 */
package unittests;

import java.awt.Color;
import org.junit.Test;
import renderer.ImageWriter;

/**
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for
	 * {@link renderer.ImageWriter#ImageWriter(java.lang.String, int, int)}.
	 */
	@Test
	public void testImageWriter() {
	    var writer=new ImageWriter("firstImage",800,500);
	    for (int j = 0; j < 800; j++) {
			for (int i = 0; i < 500; i++) {
				if(i % 50 == 0 || j % 50 ==0 || i == 499 || j ==799 )
					writer.writePixel(j, i, new primitives.Color(Color.black));
				else writer.writePixel(j, i, new primitives.Color(Color.blue));
			}
		}
	    writer.writeToImage();
	}
}
