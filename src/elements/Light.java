/**
 * 
 */
package elements;

import primitives.Color;

/**
 * The class represents all light sources at an abstract level
 * 
 * @author david and matan
 */
abstract class Light {

	protected Color intensity;

	/**
	 * get the ambient light source
	 * 
	 * @return the intensity - ambient light source
	 */
	public Color getIntensity() {
		return intensity;
	}

	/**
	 * A Ctor who gets the color and power (intensity) of light
	 * 
	 * @param color - light intensity according to RGB
	 */
	protected Light(Color color) {
		intensity = color;
	}

}
