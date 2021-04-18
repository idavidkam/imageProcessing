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
public class AmbientLight {

	private Color intensity;
	
	/**
	 * get the ambient light source
	 * @return the intensity - ambient light source
	 */
	public Color getIntensity() {
		return intensity;
	}

	/**
	 * A Ctor who gets the color and power of light
	 * @param Ia - Fill the light intensity according to RGB
	 * @param Ka - Coefficient of attenuation of filler light
	 */
	public AmbientLight(Color Ia ,double Ka) {
		intensity=Ia.scale(Ka);
	}
}
