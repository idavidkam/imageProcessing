/**
 * 
 */
package renderer;

import primitives.*;
import scene.Scene;

/**
 * 
 * @author David and matan
 *
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
	 * @return
	 */
	public abstract Color traceRay(Ray ray); 
	
}
