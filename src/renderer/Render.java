/**
 * 
 */
package renderer;

import java.util.MissingResourceException;

import elements.*;
import primitives.*;
/**
 * Whose role is to create from the scene the color matrix of the image
 * 
 * @author David and Matan
 *
 */
public class Render {

	private Camera camera;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	/**
	 * ------------- setter -----------------
	 * 
	 * @param camera the camera to set
	 * @return the {@link #Render} itself
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param imageWriter the imageWriter to set
	 * @return the {@link #Render} itself
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param rayTracer the rayTracer to set
	 * @return the {@link #Render} itself
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	/**
	 * Write scene to image
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("there is no image ", "imageWriter", null);
		imageWriter.writeToImage();
	}

	/**
	 * The function goes through all the pixels of the ViewPlane, and set color on
	 * every pixel.
	 */
	public void renderImage() {
		if (this.camera == null || this.imageWriter == null || this.rayTracer == null)
			throw new MissingResourceException("missing info. about image", null, null);
		int Nx = imageWriter.getNx();
		int Ny = imageWriter.getNy();
		for (int i = 0; i < Ny; i++) {
			for (int j = 0; j < Nx; j++) {
				var ray = camera.constructRayThroughPixel(Nx, Ny, j, i);
				var color = rayTracer.traceRay(ray);
				imageWriter.writePixel(j, i, color);
			}
		}
	}

	/**
	 * print grid (size of interval value) on image by color.
	 * 
	 * @param interval - The amount of pixels put in the grid
	 * @param color    - color of grid
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("there is no image ", "imageWriter", null);
		int Nx = imageWriter.getNx();
		int Ny = imageWriter.getNy();
		for (int i = 0; i < Ny; i++) {
			for (int j = 0; j < Nx; j++) {
				if (i % interval == 0 || j % interval == 0)
					imageWriter.writePixel(j, i, color);
			}
		}
	}
}
