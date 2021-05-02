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
	 * A Ctor who gets the color, power of light and point(source of light). the
	 * light source cannot be on a body surface
	 * 
	 * @param i0    - light intensity according to RGB
	 * @param point - position of source light
	 * @param kC    - constant coefficient
	 * @param kL    - Linear coefficient
	 * @param kQ    - Quadratic coefficient
	 */
	public PointLight(Color i0, Point3D point, double kC, double kL, double kQ) {
		super(i0);
		position = point;
		this.kC = kC;
		this.kL = kL;
		this.kQ = kQ;
	}

	@Override
	public Color getIntensity(Point3D p) {
		var distSquared = p.distanceSquared(position);
		var distance = Math.sqrt(distSquared);
		return intensity.reduce(kC + kL * distance + kQ * distSquared);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

}
