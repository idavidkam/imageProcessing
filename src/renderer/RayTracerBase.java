/**
 * 
 */
package renderer;

import primitives.*;
import scene.Scene;

/**
 * An abstract base class responsible for tracking the ray
 * @author David and matan
 */
public abstract class RayTracerBase {
	
	protected Scene scene;

	/**
	 * Ctor - get scene and set it
	 * @param scene 
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * 
	 * @param ray
	 * @return the color of pixel in current tracing ray
	 */
	public abstract Color traceRay(Ray ray); 
	
}
