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
	 * light source cannot be on a body surface. Default values for kC,kL,kQ
	 * 
	 * @param i0    - light intensity according to RGB
	 * @param point - position of source light
	 */
	public PointLight(Color i0, Point3D point) {
		super(i0);
		position = point;
		this.kC = 1;
		this.kL = 0;
		this.kQ = 0;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kC the constant coefficient to set
	 * @return the {@link #PointLight(Color, Point3D)} itself
	 */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kL the Linear coefficient to set
	 * @return the {@link #PointLight(Color, Point3D)} itself
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * 
	 * @param kQ the Quadratic coefficient to set
	 * @return the {@link #PointLight(Color, Point3D)} itself
	 */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
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
