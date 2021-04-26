/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class represents a point light source such as a simple lamp.
 */
public class PointLight extends Light implements LightSource {

	private Point3D position;
	private double kC, kL, kQ;

	/**
	 * A Ctor who gets the color, power of light and point(source of light).
	 * 
	 * @param iA    - Fill the light intensity according to RGB
	 * @param kA    - Coefficient of attenuation of filler light
	 * @param point - position of source light
	 * @param kC - constant coefficient
	 * @param kL - Linear coefficient
	 * @param kQ - Quadratic coefficient
	 */
	protected PointLight(Color iA, double kA, Point3D point, double kC, double kL, double kQ) {
		super(iA, kA);
		position = point;
		this.kC=kC;
		this.kL=kL;
		this.kQ=kQ;
	}

	@Override
	public Color getIntensity(Point3D p) {
		var distance=p.distance(position);
		return super.getIntensity().reduce(kC+kL*distance+kQ*distance*distance);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position);
	}

}
