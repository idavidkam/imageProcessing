/**
 * 
 */
package elements;

import primitives.Color;

/**
 * The class represents all light sources at an abstract level
 * @author david and matan
 */
 abstract class Light {

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
	 * @param iA - Fill the light intensity according to RGB
	 * @param kA - Coefficient of attenuation of filler light
	 */
	protected Light(Color iA ,double kA) {
		intensity=iA.scale(kA);
	}

}
