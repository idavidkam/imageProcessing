/**
 * 
 */
package renderer;


import elements.*;
import primitives.*;
import scene.*;

/**
 * Whose role is to create from the scene the color matrix of the image
 * @author David and Matan
 *
 */
public class Render {
	
	private Scene scene;
	private Camera camera;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	
	
	/**
	 * @param scene the scene to set
	 * @return the {@link #Render} himself
	 */
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}

	/**
	 * @param camera the camera to set
	 * @return the {@link #Render} himself
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * @param imageWriter the imageWriter to set
	 * @return the {@link #Render} himself
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param rayTracer the rayTracer to set
	 * @return the {@link #Render} himself
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}
    
	/**
	 * 
	 */
	public void writeToImage() {
		
	}
	
	/**
	 * 
	 */
    public void renderImage() {
		
	}
	
    /**
     * 
     * @param interval
     * @param color
     */
	public void printGrid(int interval, Color color) {
		
	}
}
