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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Ray= " + axisRay + ", radius= " + radius;
	}

}
