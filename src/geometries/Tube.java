package geometries;

import primitives.*;

/**
 * A class representing a Tube in a three-dimensional Cartesian system
 * 
 * @author david and matan
 *
 */
public class Tube implements Geometry {

	protected Ray axisRay;
	protected double radius;

	/**
	 * getter the Ray of Tube
	 * 
	 * @return a Axis Ray of Tube
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * getter the radius of Tube
	 * 
	 * @return a radius of Tube
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Tube constructor receiving a Ray value and radius.
	 * 
	 * @param axisRay - a Axis Ray of Tube
	 * @param radius  - a radius of Tube
	 */
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		var t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
		if(Util.isZero(t))
			throw new IllegalArgumentException("point is in front of the head Ray"); 
		var o = axisRay.getP0().add(axisRay.getDir().scale(t));
		return point.subtract(o).normalize();
	}

	@Override
	public String toString() {
		return "Ray= " + axisRay + ", radius= " + radius;
	}

}
