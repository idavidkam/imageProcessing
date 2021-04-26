/**
 * 
 */
package elements;

import primitives.Color;
/**
 * An ambient light source represents a fixed intensity and fixed 
 * color light source that affects all objects in the scene equally.
 * 
 * @author david and matan
 */
public class AmbientLight extends Light {

	
	
	

	/**
	 * A Ctor who gets the color and power of light
	 * @param iA - Fill the light intensity according to RGB
	 * @param kA - Coefficient of attenuation of filler light
	 */
	public AmbientLight(Color iA ,double kA) {
		super(iA,kA);
	}
}
