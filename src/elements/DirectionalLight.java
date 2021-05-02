/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class represents a directional light source such as sun or moon
 * 
 * @author david and matan
 *
 */
public class DirectionalLight extends Light implements LightSource {

	private final Vector dir;

	/**
	 * A Ctor who gets the color, power of light and direction vector.
	 * 
	 * @param i0        - light intensity according to RGB
	 * @param direction - direction of light
	 */
	public DirectionalLight(Color i0, Vector direction) {
		super(i0);
		dir = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {
		return intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return dir;
	}

}
